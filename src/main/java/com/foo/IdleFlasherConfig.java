package com.foo;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("idleflasher")
public interface IdleFlasherConfig extends Config
{
	@ConfigSection(
			name = "When Idle",
			description = "Flash/Tint the whole screen when idle.",
			position = 1
	)
	String sectionIdleTint = "idleTint";
	@ConfigSection(
			name = "When Active",
			description = "Tint the whole screen when active.",
			position = 2,
			closedByDefault = true
	)
	String sectionActiveTint = "activeTint";

	@ConfigItem(
			keyName = "idleTint",
			name = "Enabled",
			description = "Flash/Tint the whole screen when idle.",
			section = sectionIdleTint,
			position = 1
	)
	default boolean idleTint()
	{
		return true;
	}

	@Range(min = 0)
	@Units(value = Units.MILLISECONDS)
	@ConfigItem(
			keyName = "idleTintDelay",
			name = "Delay",
			description = "Milliseconds to wait after becoming idle before flashing/tinting.",
			section = sectionIdleTint,
			position = 2
	)
	default int idleTintDelay()
	{
		return 1200;
	}

	@Range(min = 0)
	@Units(value = Units.MILLISECONDS)
	@ConfigItem(
			keyName = "idleTintClickDelay",
			name = "Clicking Disables For",
			description = "Milliseconds to prevent flashing/tinting after a click.",
			section = sectionIdleTint,
			position = 3
	)
	default int idleTintClickDelay()
	{
		return 600;
	}

	@Range(min = 0)
	@Units(value = Units.MILLISECONDS)
	@ConfigItem(
			keyName = "idleTintKeyboardDelay",
			name = "Keypress Disables For",
			description = "Milliseconds to prevent flashing/tinting after keyboard activity.",
			section = sectionIdleTint,
			position = 4
	)
	default int idleTintKeyboardDelay()
	{
		return 3000;
	}
	@Alpha
	@ConfigItem(
			keyName = "idleTintColor",
			name = "Color",
			description = "Color used.",
			section = sectionIdleTint,
			position = 5
	)
	default Color idleTintColor()
	{
		return new Color(Color.PINK.getRed(),Color.PINK.getGreen(),Color.PINK.getBlue(), 64);
	}
	@ConfigItem(
			keyName = "idleTintFlash",
			name = "Flashing",
			description = "Flash between two colors.",
			section = sectionIdleTint,
			position = 6
	)
	default boolean idleTintFlash()
	{
		return true;
	}
	@Alpha
	@ConfigItem(
			keyName = "idleTintColor2",
			name = "Color 2",
			description = "Second color used when flashing.",
			section = sectionIdleTint,
			position = 7
	)
	default Color idleTintColor2()
	{
		return new Color(Color.BLACK.getRed(),Color.BLACK.getGreen(),Color.BLACK.getBlue(), 64);
	}
	@Range(min = 1)
	@Units(value = Units.MILLISECONDS)
	@ConfigItem(
			keyName = "idleTintColor1Time",
			name = "Color 1 Time",
			description = "Milliseconds to show color 1 when flashing.",
			section = sectionIdleTint,
			position = 8
	)
	default int idleTintColor1Time()
	{
		return 800;
	}
	@Range(min = 1)
	@Units(value = Units.MILLISECONDS)
	@ConfigItem(
			keyName = "idleTintColor2Time",
			name = "Color 2 Time",
			description = "Milliseconds to show color 2 when flashing.",
			section = sectionIdleTint,
			position = 9
	)
	default int idleTintColor2Time()
	{
		return 200;
	}
	@ConfigItem(
			keyName = "activeTint",
			name = "Enabled",
			description = "Tint the whole screen when active.",
			section = sectionActiveTint,
			position = 1
	)
	default boolean activeTint()
	{
		return false;
	}
	@Alpha
	@ConfigItem(
			keyName = "activeTintColor",
			name = "Color",
			description = "Color used.",
			section = sectionActiveTint,
			position = 2
	)
	default Color activeTintColor()
	{
		return new Color(Color.GREEN.getRed(),Color.GREEN.getGreen(),Color.GREEN.getBlue(), 64);
	}
}
