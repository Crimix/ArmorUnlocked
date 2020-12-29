package com.black_dog20.armorunlocked.mixin;

import com.black_dog20.armorunlocked.Config;
import net.minecraft.util.CombatRules;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.spongepowered.asm.mixin.injection.callback.LocalCapture.*;

@Mixin(CombatRules.class)
public class CombatRulesMixin {

    @Inject(method = "getDamageAfterAbsorb(FFF)F", at = @At("RETURN"), cancellable = true, locals = CAPTURE_FAILSOFT)
    private static void getDamageAfterAbsorb(float damage, float totalArmor, float toughnessAttribute, CallbackInfoReturnable<Float> ci, float f) {
        float max = Config.FULL_UNLOCKED.get() ? 25F : 23.75F;
        float f1 = MathHelper.clamp(totalArmor - damage / f, totalArmor * 0.2F,  max);
        ci.setReturnValue(damage * (1.0F - f1 / 25.0F));
    }
}
