package website.skylorbeck.minecraft.apotheosis.mixin;

import io.netty.handler.codec.http.multipart.Attribute;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import website.skylorbeck.minecraft.apotheosis.Declarar;
import website.skylorbeck.minecraft.apotheosis.enchantment.HealthBooster;

import java.util.UUID;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(at = @At("TAIL"),method = "tick")
    public void healthBoostCheck(CallbackInfo ci) {
        LivingEntity entity = ((LivingEntity) (Object) this);
        if (entity instanceof PlayerEntity && entity.age%20==0) {
            float previousMaxHealth = entity.getMaxHealth();
            float previousHealthPercent = entity.getHealth() / previousMaxHealth;

            if (EnchantmentHelper.getEquipmentLevel(Declarar.HEALTHBOOST, entity) > 0) {
                if (entity.getAttributes().hasAttribute(EntityAttributes.GENERIC_MAX_HEALTH)) {
                    EntityAttributeInstance instance = entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
                    if (instance != null) {
                        if (!instance.hasModifier(Declarar.healthBoostEAM(entity))) {
                            instance.addTemporaryModifier(Declarar.healthBoostEAM(entity));
                        } else if (instance.getModifier(Declarar.healthBoostUUID).getValue() != Declarar.healthBoostEAM(entity).getValue()) {
                            instance.removeModifier(Declarar.healthBoostUUID);
                            instance.addTemporaryModifier(Declarar.healthBoostEAM(entity));
                        }
                    }
                }
            } else {
                if (entity.getAttributes().hasAttribute(EntityAttributes.GENERIC_MAX_HEALTH)) {
                    EntityAttributeInstance instance = entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
                    if (instance != null) {
                        if (instance.hasModifier(Declarar.healthBoostEAM(entity))) {
                            instance.removeModifier(Declarar.healthBoostUUID);
                        }
                    }
                }
            }

            float afterMaxHealth = entity.getMaxHealth();
            if (afterMaxHealth != previousMaxHealth) {
                entity.setHealth(afterMaxHealth * previousHealthPercent);
            }


            if (EnchantmentHelper.getEquipmentLevel(Declarar.KNOCKBACKRESIST, entity) > 0) {
                if (entity.getAttributes().hasAttribute(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE)) {
                    EntityAttributeInstance instance = entity.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
                    if (instance != null) {
                        if (!instance.hasModifier(Declarar.knockbackResistEAM(entity))) {
                            instance.addTemporaryModifier(Declarar.knockbackResistEAM(entity));
                        } else if (instance.getModifier(Declarar.knockbackResistUUID).getValue() != Declarar.knockbackResistEAM(entity).getValue()) {
                            instance.removeModifier(Declarar.knockbackResistUUID);
                            instance.addTemporaryModifier(Declarar.knockbackResistEAM(entity));
                        }
                    }
                }
            } else {
                if (entity.getAttributes().hasAttribute(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE)) {
                    EntityAttributeInstance instance = entity.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
                    if (instance != null) {
                        if (instance.hasModifier(Declarar.knockbackResistEAM(entity))) {
                            instance.removeModifier(Declarar.knockbackResistUUID);
                        }
                    }
                }
            }


            if (EnchantmentHelper.getEquipmentLevel(Declarar.ARMORSHARPNESS, entity) > 0) {
                if (entity.getAttributes().hasAttribute(EntityAttributes.GENERIC_ATTACK_DAMAGE)) {
                    EntityAttributeInstance instance = entity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                    if (instance != null) {
                        if (!instance.hasModifier(Declarar.armorsharpnessEAM(entity))) {
                            instance.addTemporaryModifier(Declarar.armorsharpnessEAM(entity));
                        } else if (instance.getModifier(Declarar.armorsharpnessUUID).getValue() != Declarar.armorsharpnessEAM(entity).getValue()) {
                            instance.removeModifier(Declarar.armorsharpnessUUID);
                            instance.addTemporaryModifier(Declarar.armorsharpnessEAM(entity));
                        }
                    }
                }
            } else {
                if (entity.getAttributes().hasAttribute(EntityAttributes.GENERIC_ATTACK_DAMAGE)) {
                    EntityAttributeInstance instance = entity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                    if (instance != null) {
                        if (instance.hasModifier(Declarar.armorsharpnessEAM(entity))) {
                            instance.removeModifier(Declarar.armorsharpnessUUID);
                        }
                    }
                }
            }
        }
    }
}