package website.skylorbeck.minecraft.apotheosis.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import website.skylorbeck.minecraft.apotheosis.powers.MarksmanArrowCyclingPower;
import website.skylorbeck.minecraft.apotheosis.powers.MarksmanBigGamePower;

import java.util.List;

@Mixin(ArrowEntity.class)
public class ArrowEntityMixin {

    @Inject(at = @At(value = "INVOKE"), method = "onHit", cancellable = true)
    private void minDamageInject(LivingEntity target, CallbackInfo ci) {
        Entity shooter = ((ArrowEntity) (Object) this).getEffectCause();
        if (shooter.isPlayer()) {
            if (target.getHealth() >= target.getMaxHealth() / 2) {
                if (PowerHolderComponent.hasPower(shooter, MarksmanBigGamePower.class)) {
                    ((ArrowEntity) (Object) this).setDamage(((ArrowEntity) (Object) this).getDamage() + 5D);
                    target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60));
                }
            }
            if (PowerHolderComponent.hasPower(shooter, MarksmanArrowCyclingPower.class)) {
                MarksmanArrowCyclingPower marksmanArrowCyclingPower = PowerHolderComponent.KEY.get(shooter).getPowers(MarksmanArrowCyclingPower.class).get(0);
                target.addStatusEffect(marksmanArrowCyclingPower.getStatusEffect());
            }
        }
    }
}
