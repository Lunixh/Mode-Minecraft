/*package fr.lunix.bettermc.init;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.Block;

public class ReaperItem extends TieredItem {

    public ReaperItem(float p_150810_, float p_150811_, Tier p_150812_, Tag<Block> p_150813_, Item.Properties p_150814_) {
        super(p_150812_, p_150814_);
        this.blocks = p_150813_;
        this.speed = p_150812_.getSpeed();
        this.attackDamageBaseline = p_150810_ + p_150812_.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", (double)this.attackDamageBaseline, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", (double)p_150811_, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }
}*/
