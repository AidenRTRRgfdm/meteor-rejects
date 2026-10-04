package anticope.rejects.mixin.meteor;

import anticope.rejects.utils.accounts.CustomYggdrasilAccount;
import meteordevelopment.meteorclient.systems.accounts.Account;
import meteordevelopment.meteorclient.systems.accounts.Accounts;
import meteordevelopment.meteorclient.utils.misc.NbtException;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Accounts.class, remap = false)
public class AccountsMixin {
    @Inject(method = "lambda$fromTag$1(Lnet/minecraft/nbt/Tag;)Lmeteordevelopment/meteorclient/systems/accounts/Account;", at = @At("HEAD"), cancellable = true)
    private static void onFromTag(Tag tag, CallbackInfoReturnable<Account<?>> cir) {
        if (tag instanceof CompoundTag accountTag && accountTag.getStringOr("type", "").equals("Yggdrasil")) {
            try {
                CustomYggdrasilAccount account = new CustomYggdrasilAccount(null, null, null).fromTag(accountTag);
                cir.setReturnValue(account);
            } catch (NbtException exception) {
                cir.setReturnValue(null);
            }
        }
    }
}
