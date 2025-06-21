package com.example.examplemod.items;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntityType;

public class LaserGunItem extends Item {

    public LaserGunItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            Arrow arrow = new Arrow(EntityType.ARROW, level);
            arrow.setOwner(player);
            arrow.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
            arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
            arrow.setBaseDamage(5.0);
            arrow.setRemainingFireTicks(100);
            arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;

            level.addFreshEntity(arrow);
        }
        return InteractionResult.SUCCESS;
    }
}
