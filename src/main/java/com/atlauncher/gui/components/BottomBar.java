/*
 * ATLauncher - https://github.com/ATLauncher/ATLauncher
 * Copyright (C) 2013 ATLauncher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.atlauncher.gui.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.atlauncher.LogManager;
import com.atlauncher.utils.Utils;

public abstract class BottomBar extends JPanel {
    private static final long serialVersionUID = -7488195680365431776L;

    protected final JButton githubIcon = new SMButton("/assets/image/GitHubIcon.png", "GitHub");
    protected final JButton twitterIcon = new SMButton("/assets/image/TwitterIcon.png", "Twitter");

    protected final JPanel rightSide = new JPanel(new FlowLayout());

    public BottomBar() {
        super(new BorderLayout());
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setPreferredSize(new Dimension(0, 50));
        this.add(this.rightSide, BorderLayout.EAST);
        this.setupSocialButtonListeners();
        this.rightSide.add(this.githubIcon);
        this.rightSide.add(this.twitterIcon);
    }

    private void setupSocialButtonListeners() {
        githubIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogManager.info("Opening Up LexLauncher GitHub Page");
                Utils.openBrowser("https://github.com/LexMinecraft/Launcher");
            }
        });
        twitterIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogManager.info("Opening Up LexteamJamie's Twitter Page");
                Utils.openBrowser("https://twitter.com/LexteamJamie");
            }
        });
    }
}