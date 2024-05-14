module Core {

    //spring framework
    requires spring.core;
    requires spring.context;
    requires spring.beans;

    requires Common;
    requires CommonBullet;    
    requires javafx.graphics;
    exports dk.sdu.mmmi.cbse.main;
    opens dk.sdu.mmmi.cbse.main to javafx.graphics, spring.core;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

}


