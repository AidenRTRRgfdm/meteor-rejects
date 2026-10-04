package anticope.rejects.mixin.meteor.modules;

import anticope.rejects.mixininterface.IInventoryTweaks;
import meteordevelopment.meteorclient.systems.modules.misc.InventoryTweaks;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = InventoryTweaks.class, remap = false)
public abstract class InventoryTweaksMixin implements IInventoryTweaks {
    private Runnable callback;

    @Inject(method = "moveSlots", at = @At("RETURN"))
    private void afterSteal(AbstractContainerMenu handler, int start, int end, boolean steal, CallbackInfo info) {
        if (steal && callback != null) {
            Runnable completed = callback;
            callback = null;
            completed.run();
        }
    }

    @Override
    public void stealCallback(Runnable callback) {
        this.callback = callback;
    }

    @Inject(method = "checkAutoStealSettings", at = @At("HEAD"))
    private void onStealChanged(CallbackInfo info) {
        callback = null;
    }
}
