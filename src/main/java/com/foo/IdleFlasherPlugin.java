package com.foo;

import com.google.inject.Provides;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Player;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;

import static net.runelite.api.AnimationID.IDLE;

@Slf4j
@PluginDescriptor(
	name = "Idle Flasher"
)
public class IdleFlasherPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private IdleFlasherConfig config;

	@Inject
	private OverlayManager overlayManager;
	@Inject
	private TintOverlay tintOverlay;

	private long lastActive = client.getMouseLastPressedMillis();

	@Getter(AccessLevel.PACKAGE)
	private boolean idle = true; // TODO make different levels of idle for different durations.


	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(tintOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(tintOverlay);
	}

	@Provides
	IdleFlasherConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(IdleFlasherConfig.class);
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		lastActive = System.currentTimeMillis();
		idle = false;
	}

	@Subscribe
	public void onClientTick(ClientTick tick) {
		// TODO would moving some of this to onGameTick make things more efficient?
		if (client.getGameState() == GameState.LOGGED_IN) {
			Player p = client.getLocalPlayer();
			if (p.getAnimation() != IDLE || p.getPoseAnimation() != p.getIdlePoseAnimation() || p.getInteracting() != null) {
				lastActive = System.currentTimeMillis();
				idle = false;
			} else {
				// Disable for a tick if they just clicked.
				if (System.currentTimeMillis() - client.getMouseLastPressedMillis() < 600) {
					idle = false;
				} else {
					idle = (System.currentTimeMillis() - lastActive >= config.idleTintDelay());
				}
			}
		}
	}

	public Color currentidleTintColor() {
		if (config.idleTintFlash()) {
			int ms_in_cycle = (int) (System.currentTimeMillis() % (config.idleTintColor1Time() + config.idleTintColor2Time()));
			if (ms_in_cycle >= config.idleTintColor1Time()) {
				return config.idleTintColor2();
			}
		}
		return config.idleTintColor();
	}
}
