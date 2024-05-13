import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module PlayerSplitPackage {
    requires Common;
    provides IGamePluginService with dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
}