package com.enderdevelopment.knightarmormod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public class KnightArmorMod implements ModInitializer {
    public static final String MOD_ID = "knight_armor_mod";

    // Armor materials for each knight set
    public static final ArmorMaterial[] KNIGHT_MATERIALS = new ArmorMaterial[100];

    @Override
    public void onInitialize() {
        // Initialize armor materials
        for (int i = 0; i < 100; i++) {
            KNIGHT_MATERIALS[i] = new ArmorMaterial() {
                @Override
                public int getDurabilityForType(ArmorItem.Type type) {
                    return new int[]{13, 15, 16, 11}[type.ordinal()] * 37;
                }

                @Override
                public int getDefenseForType(ArmorItem.Type type) {
                    return new int[]{3, 6, 8, 3}[type.ordinal()];
                }

                @Override
                public int getEnchantmentValue() {
                    return 15;
                }

                @Override
                public Ingredient getRepairIngredient() {
                    return Ingredient.of(Items.DIAMOND);
                }

                @Override
                public String getName() {
                    return "knight_" + i;
                }

                @Override
                public float getToughness() {
                    return 2.0f;
                }

                @Override
                public float getKnockbackResistance() {
                    return 0.0f;
                }
            };
        }

        // Register armor items
        for (int i = 0; i < 100; i++) {
            registerArmorItems(i);
        }
    }

    private void registerArmorItems(int index) {
        String name = "knight_" + index;
        ResourceLocation helmetId = new ResourceLocation(MOD_ID, name + "_helmet");
        ResourceLocation chestplateId = new ResourceLocation(MOD_ID, name + "_chestplate");
        ResourceLocation leggingsId = new ResourceLocation(MOD_ID, name + "_leggings");
        ResourceLocation bootsId = new ResourceLocation(MOD_ID, name + "_boots");

        Registry.register(BuiltInRegistries.ITEM, helmetId, new ArmorItem(KNIGHT_MATERIALS[index], ArmorItem.Type.HELMET, new Item.Properties()));
        Registry.register(BuiltInRegistries.ITEM, chestplateId, new ArmorItem(KNIGHT_MATERIALS[index], ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        Registry.register(BuiltInRegistries.ITEM, leggingsId, new ArmorItem(KNIGHT_MATERIALS[index], ArmorItem.Type.LEGGINGS, new Item.Properties()));
        Registry.register(BuiltInRegistries.ITEM, bootsId, new ArmorItem(KNIGHT_MATERIALS[index], ArmorItem.Type.BOOTS, new Item.Properties()));
    }
}