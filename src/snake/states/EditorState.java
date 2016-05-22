package snake.states;

import snake.Handler;
import snake.editor.WorldEditor;
import snake.gfx.Assets;
import snake.tiles.Tile;
import snake.ui.ClickListener;
import snake.ui.UIImageButton;
import snake.ui.UIManager;
import snake.utils.Utils;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;

import java.awt.*;
import java.util.Map;

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
        frame.resize(baseWidth, baseHeight + 140);

        //adding text field to frame
        fileNameInputField = new JTextField(10);
        fileNameInputField.setSize(50, 15);
        fileNameInputField.setLocation(baseWidth/2, baseHeight + 40);
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

        g.drawImage(Assets.tilePalette, 0, 360, 250, 100, null);
        //TODO: rework following showing brush workaround
        Tile tile = worldEditor.getBrush();
        tile.render(g, 220, 405);


        if (uiManager != null) {
            uiManager.render(g);
        }
    }

    @Override
    public void createUIManager() {
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.add(new UIImageButton(289, 385, 171, 57, Assets.editorButtonSave, new ClickListener() {
            @Override
            public void onClick() {
                String nameInput = fileNameInputField.getText();
                //TODO: validate name, check if such file already exists in Utils or WorldEditor class
                if (nameInput != null){
                    fileName = nameInput;
                }
                List<String> world = worldEditor.worldToText();
                Utils.saveStringToFile(world, fileName);
            }
        }));

        uiManager.add(new UIImageButton(460, 385, 171, 57, Assets.gameButtonMenu, new ClickListener() {
            @Override
            public void onClick() {
                //TODO: rework this prototype of switching window size!
                handler.getMouseManager().setUIManager(null);
                handler.setState(new MenuState(handler));
                handler.getDisplay().getCanvas().setMaximumSize(new Dimension(baseWidth, baseHeight));
                handler.getDisplay().getCanvas().setMinimumSize(new Dimension(baseWidth, baseHeight));
                handler.getDisplay().getFrame().remove(fileNameInputField);
                handler.getDisplay().getFrame().setMaximumSize(new Dimension(baseWidth, baseHeight));
                handler.getDisplay().getFrame().setMinimumSize(new Dimension(baseWidth, baseHeight));
                handler.getDisplay().getFrame().pack();
            }
        }));

        int tileStarterX = 10;
        int tileStarterY = 382;
        int step = 3;
        Map<Boolean, BufferedImage> dirtButton = new HashMap<>();
        dirtButton.put(true, Assets.dirt);
        dirtButton.put(false, Assets.dirt);
        uiManager.add(new UIImageButton(tileStarterX, tileStarterY, 20, 20, dirtButton, new ClickListener() {
            @Override
            public void onClick() {
                worldEditor.setBrush(Tile.dirtTile);
            }
        }));
        Map<Boolean, BufferedImage> grassButton = new HashMap<>();
        grassButton.put(true, Assets.grass);
        grassButton.put(false, Assets.grass);
        uiManager.add(new UIImageButton(tileStarterX + step + 20, tileStarterY, 20, 20, grassButton, new ClickListener() {
            @Override
            public void onClick() {
                worldEditor.setBrush(Tile.grassTile);
            }
        }));
        Map<Boolean, BufferedImage> stoneButton = new HashMap<>();
        stoneButton.put(true, Assets.stone);
        stoneButton.put(false, Assets.stone);
        uiManager.add(new UIImageButton(tileStarterX + (step + 20) * 2, tileStarterY, 20, 20, stoneButton, new ClickListener() {
            @Override
            public void onClick() {
                worldEditor.setBrush(Tile.stoneTile);
            }
        }));
        Map<Boolean, BufferedImage> sandButton = new HashMap<>();
        sandButton.put(true, Assets.sand);
        sandButton.put(false, Assets.sand);
        uiManager.add(new UIImageButton(tileStarterX + (step + 20) * 3, tileStarterY, 20, 20, sandButton, new ClickListener() {
            @Override
            public void onClick() {
                worldEditor.setBrush(Tile.sandTile);
            }
        }));
        Map<Boolean, BufferedImage> waterButton = new HashMap<>();
        waterButton.put(true, Assets.water);
        waterButton.put(false, Assets.water);
        uiManager.add(new UIImageButton(tileStarterX + (step + 20) * 4, tileStarterY, 20, 20, waterButton, new ClickListener() {
            @Override
            public void onClick() {
                worldEditor.setBrush(Tile.waterTile);
            }
        }));
        Map<Boolean, BufferedImage> mossStoneButton = new HashMap<>();
        mossStoneButton.put(true, Assets.mossStone);
        mossStoneButton.put(false, Assets.mossStone);
        uiManager.add(new UIImageButton(tileStarterX + (step + 20) * 5, tileStarterY, 20, 20, mossStoneButton, new ClickListener() {
            @Override
            public void onClick() {
                worldEditor.setBrush(Tile.mossStoneTile);
            }
        }));
    }
}
