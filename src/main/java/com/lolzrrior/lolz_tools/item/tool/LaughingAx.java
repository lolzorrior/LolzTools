package com.lolzrrior.lolz_tools.item.tool;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;


public class LaughingAx extends AxeItem {
    public LaughingAx(ToolMaterial material, float attackDamage, float attackSpeed,net.minecraft.item.Item.Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Only execute the explosion logic on the server side to prevent desync
        if (!target.getWorld().isClient()) {
            // Alternate way to create an explosion. Finally got the World function createExplosion to work though, so I deprecated this code.
            //ExplosionImpl explosion = new ExplosionImpl((ServerWorld) target.getWorld(), target, Explosion.createDamageSource(attacker.getWorld(), attacker), null,
            //        target.getPos(), 1.0F, false, Explosion.DestructionType.KEEP);
            //explosion.explode();

            // World createExplosion function. Creates an explosion at the target's position when attacked with the laughing ax. This function intentionally damages the player if close enough and doesn't create fire.
            target.getWorld().createExplosion(target, Explosion.createDamageSource(attacker.getWorld(), attacker), null,  target.getPos(), 1.0F, false, World.ExplosionSourceType.MOB);
            if (attacker instanceof PlayerEntity) {
                ((PlayerEntity) attacker).sendMessage(Text.translatable("text.lolz_tools.laughing_ax.use"), true);
            }
            // Damage the item by 1 durability point
            stack.damage(1, null);
        }
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        // Checks if the item is on cooldown
        if(user.getItemCooldownManager().isCoolingDown(user.getStackInHand(hand))) {
            return ActionResult.PASS;
        }

        // Call the swing hand method on the client to let the source player see the animation too
        if(world.isClient()) {
            user.swingHand(hand);
        }

        // Only execute the explosion logic on the server side to prevent desync
        if (!world.isClient()) {

            // Alternate way to create an explosion. Finally got the World function createExplosion to work though, so I deprecated this code.
            ////ServerWorld world, @Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, Vec3d pos, float power, boolean createFire, Explosion.DestructionType destructionType
            //ExplosionImpl explosion = new ExplosionImpl((ServerWorld) world, user, Explosion.createDamageSource(world, user), null,
            //        user.getPos(), 1.0F, false, Explosion.DestructionType.KEEP);
            //explosion.explode();

            // Uses the swing method on the server so others see the animation
            user.swingHand(hand);
            // World createExplosion function. Creates an explosion at the user's position when right-clicking with the laughing ax. This function intentionally damages the play and doesn't create fire.
            // We set the source entity as null to bypass the logic that would prevent this from hurting the player.
            //createExplosion(@Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, double x, double y, double z, float power, boolean createFire, World.ExplosionSourceType explosionSourceType, ParticleEffect smallParticle, ParticleEffect largeParticle, RegistryEntry<SoundEvent> soundEvent)
            world.createExplosion(null, Explosion.createDamageSource(user.getWorld(), user), null,  user.getPos(), 1.0F, false, World.ExplosionSourceType.MOB);
            // Sends player a message indicating the ax was used.
            user.sendMessage(Text.translatable("text.lolz_tools.laughing_ax.use"), true);
            // Damages the ax by 1 durability point
            user.getActiveItem().damage(1, user);
            // Sets a cooldown of 100 ticks (5 seconds) on the item
            user.getItemCooldownManager().set(user.getStackInHand(hand), 100);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        // Checks if the item is on cooldown
        if (context.getPlayer() != null && context.getPlayer().getItemCooldownManager().isCoolingDown(context.getStack())) {
            return ActionResult.PASS;
        }

        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity user = context.getPlayer();

        // Call the swing hand method on the client to let the source player see the animation too
        if (world.isClient() && user != null) {
            user.swingHand(context.getHand());
        }

        // Only execute the login on the server to prevent desync
        if (!world.isClient() && user != null) {

            // Swing hand on the server
            user.swingHand(context.getHand());
            // Creates an explosion on the target block when right-clicking with the ax. This action intentionally damages the player
            world.createExplosion(null, Explosion.createDamageSource(world, user), null,  Vec3d.of(blockPos), 1.0F, false, World.ExplosionSourceType.MOB);
            // Sends the player a message indicating the ax was used.
            user.sendMessage(Text.translatable("text.lolz_tools.laughing_ax.use"), true);
            // Sets a cooldown of 100 ticks (5 seconds) on the item
            user.getActiveItem().damage(1, user);

            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
