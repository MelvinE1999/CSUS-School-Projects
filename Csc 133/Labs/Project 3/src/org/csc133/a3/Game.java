package org.csc133.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import org.csc133.commands.*;


public class Game extends Form implements Runnable{
    private final GameWorld gw;
    private final GlassCockpit cockPitView;
    private final MapView mapView;
    private final UITimer timer;
    private final GameTickCommand gameTickCommand;
    private final ExitCommand exitCommand;
    private final AccelerateCommand accelerateCommand;
    private final LeftTurnCommand leftTurnCommand;
    private final BrakeCommand brakeCommand;
    private final RightTurnCommand rightTurnCommand;
    private final SoundToggleCommand soundCommand;


    public Game(){
        this.setLayout(new BorderLayout());
        gw = new GameWorld();

        accelerateCommand = new AccelerateCommand(gw);
        leftTurnCommand = new LeftTurnCommand(gw);
        brakeCommand = new BrakeCommand(gw);
        rightTurnCommand = new RightTurnCommand(gw);
        gameTickCommand = new GameTickCommand(gw);
        soundCommand = new SoundToggleCommand(gw);
        exitCommand = new ExitCommand();

        cockPitView = new GlassCockpit(gw);
        this.add(BorderLayout.NORTH,cockPitView);

        mapView = new MapView(gw);
        this.add(BorderLayout.CENTER, mapView);

        GameObject.setMapView(mapView);

        createToolBar();
        setUpButtons();

        gw.init();
        GameObject.setGw(gw);
        addKeyListeners();
        show();

        gw.create();

        timer = new UITimer(this);
        timer.schedule(20, true, this);

        GameWorld.setGameHeight(mapView.getHeight());
        GameWorld.setGameWidth(mapView.getWidth());
        mapView.setMapOrigin(new Point(mapView.getX(), mapView.getY()));
        MapView.setMapLength(mapView.getWidth());
        MapView.setMapHeight(mapView.getHeight());
    }

    @Override
    public void run(){
        gw.gameClockHasTicked();
        mapView.update();
        //gw.showMap();

    }

    private void setUpButtons(){
        Container buttonHolder = new Container((
                           (new BoxLayout(BoxLayout.X_AXIS))));
        buttonHolder.getAllStyles().setBorder(
                           Border.createLineBorder(2,ColorUtil.BLACK));

        buttonHolder.getAllStyles().setMarginLeft(390);
        buttonHolder.getAllStyles().setMarginRight(390);




        Button left = new Button(leftTurnCommand);
        left.setText(" ← ");

        left.getUnselectedStyle().setMarginRight(10);
        left.getUnselectedStyle().setMarginLeft(10);
        buttonHolder.add(left);


        Button accelerate = new Button(accelerateCommand);
        accelerate.setText(" ↑ ");
        accelerate.getUnselectedStyle().setMarginRight(9);
        accelerate.getUnselectedStyle().setMarginLeft(10);
        buttonHolder.add(accelerate);

        Button brake = new Button(brakeCommand);
        brake.setText(" ↓ ");
        brake.getUnselectedStyle().setMarginRight(9);
        brake.getUnselectedStyle().setMarginLeft(10);
        buttonHolder.add(brake);

        Button right = new Button(rightTurnCommand);
        right.getUnselectedStyle().setMarginRight(9);
        right.getUnselectedStyle().setMarginLeft(10);
        right.setText(" → ");
        buttonHolder.add(right);


        this.add(BorderLayout.SOUTH, buttonHolder);

    }

    private void createToolBar(){
        Toolbar toolbar = new Toolbar();
        this.setToolbar(toolbar);
        toolbar.setTitle("SkyMail V.2");
        toolbar.getTitleComponent().getUnselectedStyle()
                .setAlignment(Component.CENTER);

        HelpMenuCommand help = new HelpMenuCommand();
        toolbar.addCommandToOverflowMenu(help);


        ChangeStrategyCommand changeStrategyCommand =
                                                new ChangeStrategyCommand(gw);
        toolbar.addCommandToOverflowMenu(changeStrategyCommand);

        AboutMenuCommand about = new AboutMenuCommand();
        toolbar.addCommandToOverflowMenu(about);


        toolbar.addCommandToOverflowMenu(soundCommand);

        toolbar.addCommandToOverflowMenu(exitCommand);

    }

    private void addKeyListeners(){

        //needed to keep this as it is the section that reads keys for the game
        addKeyListener('t', gameTickCommand);
        addKeyListener('x', exitCommand);
        addKeyListener('r', rightTurnCommand);
        addKeyListener('b', brakeCommand);
        addKeyListener('a', accelerateCommand);
        addKeyListener('l', leftTurnCommand);

    }

}
