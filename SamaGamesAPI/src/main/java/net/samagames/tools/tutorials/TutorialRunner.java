package net.samagames.tools.tutorials;

import net.samagames.api.SamaGamesAPI;
import net.samagames.tools.Reflection;
import net.samagames.tools.Titles;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import java.util.UUID;

/*
 * This file is part of SamaGamesAPI.
 *
 * SamaGamesAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SamaGamesAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SamaGamesAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
public class TutorialRunner implements Runnable
{
    private Plugin p;

    private final String tutorialInChatPrefix = ChatColor.GRAY + "│ " + ChatColor.RESET;

    private Player player;
    private Tutorial tutorial;

    private int currentChapter = 0;
    private int currentText = 0;
    private long currentTimer = 0;

    public TutorialRunner(Tutorial tutorial, UUID playerId)
    {
	    this.p = SamaGamesAPI.get().getPlugin();

	    this.player   = p.getServer().getPlayer(playerId);
        this.tutorial = tutorial;
    }


    @Override
    public void run()
    {
        if (!player.isOnline())
        {
            tutorial.stop(player.getUniqueId(), true);
            return;
        }

        if (currentChapter == tutorial.getContent().size()) // The end.
        {
            tutorial.stop(player.getUniqueId(), false);
            return;
        }

        if (currentTimer > 0)
        {
            currentTimer -= 10;
            return;
        }

        TutorialChapter chapter = tutorial.getContent().get(currentChapter);

        // Delays of fade-in, fade-out and display
        int fadeIn = (currentText == 0) ? 10 : 0;
        int fadeOut = (currentText == chapter.getContent().size() - 1) ? 10 : 0;
        int readingTime = chapter.getContent().get(currentText).getRight().intValue() + (fadeOut == 10 ? -10 : 10);


        // New chapter, new location
        if (currentText == 0)
        {
            chapter.teleport(player);
            Reflection.playSound(player, player.getLocation(), Reflection.PackageType.getServerVersion().equals("v1_8_R3") ? "LEVEL_UP" : "ENTITY_PLAYER_LEVELUP", 1L, 2L);
        }

        // Title version
        Titles.sendTitle(
                player,
                fadeIn, readingTime, fadeOut,
                chapter.getTitle(),
                chapter.getContent().get(currentText).getLeft()
        );


        // Chat version
        if (chapter.isDisplayedInChat())
        {
            if (currentText == 0) player.sendMessage(tutorialInChatPrefix + chapter.getTitle());
            player.sendMessage(tutorialInChatPrefix + chapter.getContent().get(currentText).getLeft());
        }

        // Cooldown
        currentTimer = chapter.getContent().get(currentText).getRight() - 10;

        // Next one?
        currentText++;

        if (currentText == chapter.getContent().size())
        {
            currentChapter++;
            currentText = 0;
        }
    }
}
