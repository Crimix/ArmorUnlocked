package com.black_dog20.armorunlocked.mixin;

import com.black_dog20.armorunlocked.Config;
import net.minecraft.world.damagesource.CombatRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.util.Mth.clamp;
import static org.spongepowered.asm.mixin.injection.callback.LocalCapture.CAPTURE_FAILSOFT;

@Mixin(CombatRules.class)
public class CombatRulesMixin {

    @Inject(method = "getDamageAfterAbsorb(FFF)F", at = @At("RETURN"), cancellable = true, locals = CAPTURE_FAILSOFT)
    private static void getDamageAfterAbsorb(float damage, float totalArmor, float toughnessAttribute, CallbackInfoReturnable<Float> ci, float f) {
        float max = Config.FULL_UNLOCKED.get() ? 25F : 23.75F;
        float f1 = clamp(totalArmor - damage / f, totalArmor * 0.2F,  max);
        ci.setReturnValue(damage * (1.0F - f1 / 25.0F));
    }
}
