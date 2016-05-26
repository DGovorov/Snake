package snake.states;

import snake.Handler;
import snake.editor.WorldEditor;
import snake.gfx.Assets;
import snake.tiles.*;
import snake.tiles.woolTiles.*;
import snake.ui.ClickListener;
import snake.ui.UIImageButton;
import snake.ui.UIManager;
import snake.ui.UITileButton;
import snake.utils.Utils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import java.awt.*;

/**
 * Created by Dim on 19.05.2016.
 */
public class EditorState extends State {

    private WorldEditor worldEditor;
    private UIManager uiManager;
    private String fileName;
    private JTextField fileNameInputField;
    private int baseWidth;
    private int baseHeight;

    public EditorState(Handler handler) {
        super(handler);
        worldEditor = new WorldEditor(handler);
        createUIManager();
        fileName = "default";

        //terribly resizing window to add palette and stuff
        Canvas canvas = handler.getDisplay().getCanvas();
        Dimension dimension = canvas.getMaximumSize();
        baseWidth = (int) dimension.getWidth();
        baseHeight = (int) dimension.getHeight();
        canvas.setMaximumSize(new Dimension(baseWidth, baseHeight + 120));
        //yeah, and frame too
        JFrame frame = handler.getDisplay().getFrame();
        frame.resize(baseWidth +5, baseHeight + 140);

        //adding text field to frame
        fileNameInputField = new JTextField(10);
        fileNameInputField.setSize(50, 15);
        fileNameInputField.setLocation(baseWidth / 2, baseHeight + 40);
        fileNameInputField.setPreferredSize(new Dimension(50, 15));
        fileNameInputField.setMaximumSize(new Dimension(50, 15));
        fileNameInputField.setMinimumSize(new Dimension(50, 15));
        fileNameInputField.setText("INPUT FILE NAME HERE!!!");
        fileNameInputField.setVisible(true);
        frame.add(fileNameInputField, BorderLayout.SOUTH);
    }

    @Override
    public void tick() {
        worldEditor.tick();
        if (uiManager != null) {
            uiManager.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        worldEditor.render(g);

        g.drawImage(Assets.woodBackground, 0, 360, 640, 120, null);

        g.drawImage(Assets.tilePalette, 0, 360, 250, 100, null);
        //TODO: rework following showing brush workaround
        Tile tile = worldEditor.getBrush();
        tile.render(g, 220, 405);


        if (uiManager != null) {
            uiManager.render(g);
        }
    }

    private static void infoBox(String infoMessage) {
        JOptionPane.showMessageDialog(null, infoMessage, "Notification", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void createUIManager() {
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.add(new UIImageButton(289, 385, 171, 57, Assets.editorButtonSave, new ClickListener() {
            @Override
            public void onClick() {
                String nameInput = fileNameInputField.getText();
                if (nameInput != null) {
                    fileName = nameInput;
                }
                List<String> world = worldEditor.worldToText();
                //TODO: validate name, check if such file already exists in Utils or WorldEditor class
                //TODO: Make saveStringToFile method boolean and popup success message only when world saved properly
                Utils.saveStringToFile(world, fileName);
                infoBox("World \"" + fileName + "\" successfully created!");
            }
        }));

        uiManager.add(new UIImageButton(460, 385, 171, 57, Assets.gameButtonMenu, new ClickListener() {
            @Override
            public void onClick() {
                //TODO: rework this prototype of switching window size!
                handler.getMouseManager().setUIManager(null);
                handler.getDisplay().getCanvas().setMaximumSize(new Dimension(baseWidth, baseHeight));
                handler.getDisplay().getCanvas().setMinimumSize(new Dimension(baseWidth, baseHeight));
                handler.getDisplay().getFrame().remove(fileNameInputField);
                handler.getDisplay().getFrame().setMaximumSize(new Dimension(baseWidth, baseHeight));
                handler.getDisplay().getFrame().setMinimumSize(new Dimension(baseWidth, baseHeight));
                handler.getDisplay().getFrame().pack();
                handler.getDisplay().getFrame().requestFocus();
                handler.setState(new MenuState(handler));
            }
        }));

        addTileButtonsToUI();

    }

    private void addTileButtonsToUI() {
        int tileStarterX = 10;
        int tileStarterY = 382;
        int borderSize = 3;
        int paletteRowLength = 9;

        List<Tile> tiles = new ArrayList<>();
        tiles.add(new DirtTile());
        tiles.add(new GrassTile());
        tiles.add(new StoneTile());
        tiles.add(new SandTile());
        tiles.add(new WaterTile());
        tiles.add(new MossStoneTile());
        tiles.add(new BrickTile());
        tiles.add(new GravelTile());
        tiles.add(new SnowTile());
        tiles.add(new IronTile());
        tiles.add(new GoldTile());
        tiles.add(new DiamondTile());

        tiles.add(new WoolBlackTile());
        tiles.add(new WoolDarkGreyTile());
        tiles.add(new WoolRedTile());
        tiles.add(new WoolPinkTile());
        tiles.add(new WoolDarkGreenTile());
        tiles.add(new WoolGreenTile());
        tiles.add(new WoolBrownTile());
        tiles.add(new WoolYellowTile());
        tiles.add(new WoolBlueTile());
        tiles.add(new WoolTurquoiseTile());
        tiles.add(new WoolVioletTile());
        tiles.add(new WoolDarkPinkTile());
        tiles.add(new WoolDarkTurquoiseTile());
        tiles.add(new WoolOrangeTile());
        tiles.add(new WoolGreyTile());
        tiles.add(new WoolWhiteTile());

        for (int i = 0; i < tiles.size(); i++) {
            int xStep = i % paletteRowLength;
            int yStep = i / paletteRowLength;
            Tile currentTile = tiles.get(i);
            uiManager.add(new UITileButton(tileStarterX + (borderSize + 20) * xStep,
                    tileStarterY + (borderSize + 20) * yStep, currentTile, new ClickListener() {
                @Override
                public void onClick() {
                    worldEditor.setBrush(currentTile);
                }
            }));
        }
    }
}
