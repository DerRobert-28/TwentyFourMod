package net.minecraft.util.datafix.fixes;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.function.Function;
import net.minecraft.util.datafix.schemas.NamespacedSchema;

public class ItemIdFix extends DataFix {
    private static final Int2ObjectMap<String> ITEM_NAMES = DataFixUtils.make(new Int2ObjectOpenHashMap<>(), p_15953_ -> {
        p_15953_.put(1, "minecraft:stone");
        p_15953_.put(2, "minecraft:grass");
        p_15953_.put(3, "minecraft:dirt");
        p_15953_.put(4, "minecraft:cobblestone");
        p_15953_.put(5, "minecraft:planks");
        p_15953_.put(6, "minecraft:sapling");
        p_15953_.put(7, "minecraft:bedrock");
        p_15953_.put(8, "minecraft:flowing_water");
        p_15953_.put(9, "minecraft:water");
        p_15953_.put(10, "minecraft:flowing_lava");
        p_15953_.put(11, "minecraft:lava");
        p_15953_.put(12, "minecraft:sand");
        p_15953_.put(13, "minecraft:gravel");
        p_15953_.put(14, "minecraft:gold_ore");
        p_15953_.put(15, "minecraft:iron_ore");
        p_15953_.put(16, "minecraft:coal_ore");
        p_15953_.put(17, "minecraft:log");
        p_15953_.put(18, "minecraft:leaves");
        p_15953_.put(19, "minecraft:sponge");
        p_15953_.put(20, "minecraft:glass");
        p_15953_.put(21, "minecraft:lapis_ore");
        p_15953_.put(22, "minecraft:lapis_block");
        p_15953_.put(23, "minecraft:dispenser");
        p_15953_.put(24, "minecraft:sandstone");
        p_15953_.put(25, "minecraft:noteblock");
        p_15953_.put(27, "minecraft:golden_rail");
        p_15953_.put(28, "minecraft:detector_rail");
        p_15953_.put(29, "minecraft:sticky_piston");
        p_15953_.put(30, "minecraft:web");
        p_15953_.put(31, "minecraft:tallgrass");
        p_15953_.put(32, "minecraft:deadbush");
        p_15953_.put(33, "minecraft:piston");
        p_15953_.put(35, "minecraft:wool");
        p_15953_.put(37, "minecraft:yellow_flower");
        p_15953_.put(38, "minecraft:red_flower");
        p_15953_.put(39, "minecraft:brown_mushroom");
        p_15953_.put(40, "minecraft:red_mushroom");
        p_15953_.put(41, "minecraft:gold_block");
        p_15953_.put(42, "minecraft:iron_block");
        p_15953_.put(43, "minecraft:double_stone_slab");
        p_15953_.put(44, "minecraft:stone_slab");
        p_15953_.put(45, "minecraft:brick_block");
        p_15953_.put(46, "minecraft:tnt");
        p_15953_.put(47, "minecraft:bookshelf");
        p_15953_.put(48, "minecraft:mossy_cobblestone");
        p_15953_.put(49, "minecraft:obsidian");
        p_15953_.put(50, "minecraft:torch");
        p_15953_.put(51, "minecraft:fire");
        p_15953_.put(52, "minecraft:mob_spawner");
        p_15953_.put(53, "minecraft:oak_stairs");
        p_15953_.put(54, "minecraft:chest");
        p_15953_.put(56, "minecraft:diamond_ore");
        p_15953_.put(57, "minecraft:diamond_block");
        p_15953_.put(58, "minecraft:crafting_table");
        p_15953_.put(60, "minecraft:farmland");
        p_15953_.put(61, "minecraft:furnace");
        p_15953_.put(62, "minecraft:lit_furnace");
        p_15953_.put(65, "minecraft:ladder");
        p_15953_.put(66, "minecraft:rail");
        p_15953_.put(67, "minecraft:stone_stairs");
        p_15953_.put(69, "minecraft:lever");
        p_15953_.put(70, "minecraft:stone_pressure_plate");
        p_15953_.put(72, "minecraft:wooden_pressure_plate");
        p_15953_.put(73, "minecraft:redstone_ore");
        p_15953_.put(76, "minecraft:redstone_torch");
        p_15953_.put(77, "minecraft:stone_button");
        p_15953_.put(78, "minecraft:snow_layer");
        p_15953_.put(79, "minecraft:ice");
        p_15953_.put(80, "minecraft:snow");
        p_15953_.put(81, "minecraft:cactus");
        p_15953_.put(82, "minecraft:clay");
        p_15953_.put(84, "minecraft:jukebox");
        p_15953_.put(85, "minecraft:fence");
        p_15953_.put(86, "minecraft:pumpkin");
        p_15953_.put(87, "minecraft:netherrack");
        p_15953_.put(88, "minecraft:soul_sand");
        p_15953_.put(89, "minecraft:glowstone");
        p_15953_.put(90, "minecraft:portal");
        p_15953_.put(91, "minecraft:lit_pumpkin");
        p_15953_.put(95, "minecraft:stained_glass");
        p_15953_.put(96, "minecraft:trapdoor");
        p_15953_.put(97, "minecraft:monster_egg");
        p_15953_.put(98, "minecraft:stonebrick");
        p_15953_.put(99, "minecraft:brown_mushroom_block");
        p_15953_.put(100, "minecraft:red_mushroom_block");
        p_15953_.put(101, "minecraft:iron_bars");
        p_15953_.put(102, "minecraft:glass_pane");
        p_15953_.put(103, "minecraft:melon_block");
        p_15953_.put(106, "minecraft:vine");
        p_15953_.put(107, "minecraft:fence_gate");
        p_15953_.put(108, "minecraft:brick_stairs");
        p_15953_.put(109, "minecraft:stone_brick_stairs");
        p_15953_.put(110, "minecraft:mycelium");
        p_15953_.put(111, "minecraft:waterlily");
        p_15953_.put(112, "minecraft:nether_brick");
        p_15953_.put(113, "minecraft:nether_brick_fence");
        p_15953_.put(114, "minecraft:nether_brick_stairs");
        p_15953_.put(116, "minecraft:enchanting_table");
        p_15953_.put(119, "minecraft:end_portal");
        p_15953_.put(120, "minecraft:end_portal_frame");
        p_15953_.put(121, "minecraft:end_stone");
        p_15953_.put(122, "minecraft:dragon_egg");
        p_15953_.put(123, "minecraft:redstone_lamp");
        p_15953_.put(125, "minecraft:double_wooden_slab");
        p_15953_.put(126, "minecraft:wooden_slab");
        p_15953_.put(127, "minecraft:cocoa");
        p_15953_.put(128, "minecraft:sandstone_stairs");
        p_15953_.put(129, "minecraft:emerald_ore");
        p_15953_.put(130, "minecraft:ender_chest");
        p_15953_.put(131, "minecraft:tripwire_hook");
        p_15953_.put(133, "minecraft:emerald_block");
        p_15953_.put(134, "minecraft:spruce_stairs");
        p_15953_.put(135, "minecraft:birch_stairs");
        p_15953_.put(136, "minecraft:jungle_stairs");
        p_15953_.put(137, "minecraft:command_block");
        p_15953_.put(138, "minecraft:beacon");
        p_15953_.put(139, "minecraft:cobblestone_wall");
        p_15953_.put(141, "minecraft:carrots");
        p_15953_.put(142, "minecraft:potatoes");
        p_15953_.put(143, "minecraft:wooden_button");
        p_15953_.put(145, "minecraft:anvil");
        p_15953_.put(146, "minecraft:trapped_chest");
        p_15953_.put(147, "minecraft:light_weighted_pressure_plate");
        p_15953_.put(148, "minecraft:heavy_weighted_pressure_plate");
        p_15953_.put(151, "minecraft:daylight_detector");
        p_15953_.put(152, "minecraft:redstone_block");
        p_15953_.put(153, "minecraft:quartz_ore");
        p_15953_.put(154, "minecraft:hopper");
        p_15953_.put(155, "minecraft:quartz_block");
        p_15953_.put(156, "minecraft:quartz_stairs");
        p_15953_.put(157, "minecraft:activator_rail");
        p_15953_.put(158, "minecraft:dropper");
        p_15953_.put(159, "minecraft:stained_hardened_clay");
        p_15953_.put(160, "minecraft:stained_glass_pane");
        p_15953_.put(161, "minecraft:leaves2");
        p_15953_.put(162, "minecraft:log2");
        p_15953_.put(163, "minecraft:acacia_stairs");
        p_15953_.put(164, "minecraft:dark_oak_stairs");
        p_15953_.put(170, "minecraft:hay_block");
        p_15953_.put(171, "minecraft:carpet");
        p_15953_.put(172, "minecraft:hardened_clay");
        p_15953_.put(173, "minecraft:coal_block");
        p_15953_.put(174, "minecraft:packed_ice");
        p_15953_.put(175, "minecraft:double_plant");
        p_15953_.put(256, "minecraft:iron_shovel");
        p_15953_.put(257, "minecraft:iron_pickaxe");
        p_15953_.put(258, "minecraft:iron_axe");
        p_15953_.put(259, "minecraft:flint_and_steel");
        p_15953_.put(260, "minecraft:apple");
        p_15953_.put(261, "minecraft:bow");
        p_15953_.put(262, "minecraft:arrow");
        p_15953_.put(263, "minecraft:coal");
        p_15953_.put(264, "minecraft:diamond");
        p_15953_.put(265, "minecraft:iron_ingot");
        p_15953_.put(266, "minecraft:gold_ingot");
        p_15953_.put(267, "minecraft:iron_sword");
        p_15953_.put(268, "minecraft:wooden_sword");
        p_15953_.put(269, "minecraft:wooden_shovel");
        p_15953_.put(270, "minecraft:wooden_pickaxe");
        p_15953_.put(271, "minecraft:wooden_axe");
        p_15953_.put(272, "minecraft:stone_sword");
        p_15953_.put(273, "minecraft:stone_shovel");
        p_15953_.put(274, "minecraft:stone_pickaxe");
        p_15953_.put(275, "minecraft:stone_axe");
        p_15953_.put(276, "minecraft:diamond_sword");
        p_15953_.put(277, "minecraft:diamond_shovel");
        p_15953_.put(278, "minecraft:diamond_pickaxe");
        p_15953_.put(279, "minecraft:diamond_axe");
        p_15953_.put(280, "minecraft:stick");
        p_15953_.put(281, "minecraft:bowl");
        p_15953_.put(282, "minecraft:mushroom_stew");
        p_15953_.put(283, "minecraft:golden_sword");
        p_15953_.put(284, "minecraft:golden_shovel");
        p_15953_.put(285, "minecraft:golden_pickaxe");
        p_15953_.put(286, "minecraft:golden_axe");
        p_15953_.put(287, "minecraft:string");
        p_15953_.put(288, "minecraft:feather");
        p_15953_.put(289, "minecraft:gunpowder");
        p_15953_.put(290, "minecraft:wooden_hoe");
        p_15953_.put(291, "minecraft:stone_hoe");
        p_15953_.put(292, "minecraft:iron_hoe");
        p_15953_.put(293, "minecraft:diamond_hoe");
        p_15953_.put(294, "minecraft:golden_hoe");
        p_15953_.put(295, "minecraft:wheat_seeds");
        p_15953_.put(296, "minecraft:wheat");
        p_15953_.put(297, "minecraft:bread");
        p_15953_.put(298, "minecraft:leather_helmet");
        p_15953_.put(299, "minecraft:leather_chestplate");
        p_15953_.put(300, "minecraft:leather_leggings");
        p_15953_.put(301, "minecraft:leather_boots");
        p_15953_.put(302, "minecraft:chainmail_helmet");
        p_15953_.put(303, "minecraft:chainmail_chestplate");
        p_15953_.put(304, "minecraft:chainmail_leggings");
        p_15953_.put(305, "minecraft:chainmail_boots");
        p_15953_.put(306, "minecraft:iron_helmet");
        p_15953_.put(307, "minecraft:iron_chestplate");
        p_15953_.put(308, "minecraft:iron_leggings");
        p_15953_.put(309, "minecraft:iron_boots");
        p_15953_.put(310, "minecraft:diamond_helmet");
        p_15953_.put(311, "minecraft:diamond_chestplate");
        p_15953_.put(312, "minecraft:diamond_leggings");
        p_15953_.put(313, "minecraft:diamond_boots");
        p_15953_.put(314, "minecraft:golden_helmet");
        p_15953_.put(315, "minecraft:golden_chestplate");
        p_15953_.put(316, "minecraft:golden_leggings");
        p_15953_.put(317, "minecraft:golden_boots");
        p_15953_.put(318, "minecraft:flint");
        p_15953_.put(319, "minecraft:porkchop");
        p_15953_.put(320, "minecraft:cooked_porkchop");
        p_15953_.put(321, "minecraft:painting");
        p_15953_.put(322, "minecraft:golden_apple");
        p_15953_.put(323, "minecraft:sign");
        p_15953_.put(324, "minecraft:wooden_door");
        p_15953_.put(325, "minecraft:bucket");
        p_15953_.put(326, "minecraft:water_bucket");
        p_15953_.put(327, "minecraft:lava_bucket");
        p_15953_.put(328, "minecraft:minecart");
        p_15953_.put(329, "minecraft:saddle");
        p_15953_.put(330, "minecraft:iron_door");
        p_15953_.put(331, "minecraft:redstone");
        p_15953_.put(332, "minecraft:snowball");
        p_15953_.put(333, "minecraft:boat");
        p_15953_.put(334, "minecraft:leather");
        p_15953_.put(335, "minecraft:milk_bucket");
        p_15953_.put(336, "minecraft:brick");
        p_15953_.put(337, "minecraft:clay_ball");
        p_15953_.put(338, "minecraft:reeds");
        p_15953_.put(339, "minecraft:paper");
        p_15953_.put(340, "minecraft:book");
        p_15953_.put(341, "minecraft:slime_ball");
        p_15953_.put(342, "minecraft:chest_minecart");
        p_15953_.put(343, "minecraft:furnace_minecart");
        p_15953_.put(344, "minecraft:egg");
        p_15953_.put(345, "minecraft:compass");
        p_15953_.put(346, "minecraft:fishing_rod");
        p_15953_.put(347, "minecraft:clock");
        p_15953_.put(348, "minecraft:glowstone_dust");
        p_15953_.put(349, "minecraft:fish");
        p_15953_.put(350, "minecraft:cooked_fished");
        p_15953_.put(351, "minecraft:dye");
        p_15953_.put(352, "minecraft:bone");
        p_15953_.put(353, "minecraft:sugar");
        p_15953_.put(354, "minecraft:cake");
        p_15953_.put(355, "minecraft:bed");
        p_15953_.put(356, "minecraft:repeater");
        p_15953_.put(357, "minecraft:cookie");
        p_15953_.put(358, "minecraft:filled_map");
        p_15953_.put(359, "minecraft:shears");
        p_15953_.put(360, "minecraft:melon");
        p_15953_.put(361, "minecraft:pumpkin_seeds");
        p_15953_.put(362, "minecraft:melon_seeds");
        p_15953_.put(363, "minecraft:beef");
        p_15953_.put(364, "minecraft:cooked_beef");
        p_15953_.put(365, "minecraft:chicken");
        p_15953_.put(366, "minecraft:cooked_chicken");
        p_15953_.put(367, "minecraft:rotten_flesh");
        p_15953_.put(368, "minecraft:ender_pearl");
        p_15953_.put(369, "minecraft:blaze_rod");
        p_15953_.put(370, "minecraft:ghast_tear");
        p_15953_.put(371, "minecraft:gold_nugget");
        p_15953_.put(372, "minecraft:nether_wart");
        p_15953_.put(373, "minecraft:potion");
        p_15953_.put(374, "minecraft:glass_bottle");
        p_15953_.put(375, "minecraft:spider_eye");
        p_15953_.put(376, "minecraft:fermented_spider_eye");
        p_15953_.put(377, "minecraft:blaze_powder");
        p_15953_.put(378, "minecraft:magma_cream");
        p_15953_.put(379, "minecraft:brewing_stand");
        p_15953_.put(380, "minecraft:cauldron");
        p_15953_.put(381, "minecraft:ender_eye");
        p_15953_.put(382, "minecraft:speckled_melon");
        p_15953_.put(383, "minecraft:spawn_egg");
        p_15953_.put(384, "minecraft:experience_bottle");
        p_15953_.put(385, "minecraft:fire_charge");
        p_15953_.put(386, "minecraft:writable_book");
        p_15953_.put(387, "minecraft:written_book");
        p_15953_.put(388, "minecraft:emerald");
        p_15953_.put(389, "minecraft:item_frame");
        p_15953_.put(390, "minecraft:flower_pot");
        p_15953_.put(391, "minecraft:carrot");
        p_15953_.put(392, "minecraft:potato");
        p_15953_.put(393, "minecraft:baked_potato");
        p_15953_.put(394, "minecraft:poisonous_potato");
        p_15953_.put(395, "minecraft:map");
        p_15953_.put(396, "minecraft:golden_carrot");
        p_15953_.put(397, "minecraft:skull");
        p_15953_.put(398, "minecraft:carrot_on_a_stick");
        p_15953_.put(399, "minecraft:nether_star");
        p_15953_.put(400, "minecraft:pumpkin_pie");
        p_15953_.put(401, "minecraft:fireworks");
        p_15953_.put(402, "minecraft:firework_charge");
        p_15953_.put(403, "minecraft:enchanted_book");
        p_15953_.put(404, "minecraft:comparator");
        p_15953_.put(405, "minecraft:netherbrick");
        p_15953_.put(406, "minecraft:quartz");
        p_15953_.put(407, "minecraft:tnt_minecart");
        p_15953_.put(408, "minecraft:hopper_minecart");
        p_15953_.put(417, "minecraft:iron_horse_armor");
        p_15953_.put(418, "minecraft:golden_horse_armor");
        p_15953_.put(419, "minecraft:diamond_horse_armor");
        p_15953_.put(420, "minecraft:lead");
        p_15953_.put(421, "minecraft:name_tag");
        p_15953_.put(422, "minecraft:command_block_minecart");
        p_15953_.put(2256, "minecraft:record_13");
        p_15953_.put(2257, "minecraft:record_cat");
        p_15953_.put(2258, "minecraft:record_blocks");
        p_15953_.put(2259, "minecraft:record_chirp");
        p_15953_.put(2260, "minecraft:record_far");
        p_15953_.put(2261, "minecraft:record_mall");
        p_15953_.put(2262, "minecraft:record_mellohi");
        p_15953_.put(2263, "minecraft:record_stal");
        p_15953_.put(2264, "minecraft:record_strad");
        p_15953_.put(2265, "minecraft:record_ward");
        p_15953_.put(2266, "minecraft:record_11");
        p_15953_.put(2267, "minecraft:record_wait");
        p_15953_.defaultReturnValue("minecraft:air");
    });

    public ItemIdFix(Schema p_15940_, boolean p_15941_) {
        super(p_15940_, p_15941_);
    }

    public static String getItem(int p_15943_) {
        return ITEM_NAMES.get(p_15943_);
    }

    @Override
    public TypeRewriteRule makeRule() {
        Type<Either<Integer, Pair<String, String>>> type = DSL.or(
            DSL.intType(), DSL.named(References.ITEM_NAME.typeName(), NamespacedSchema.namespacedString())
        );
        Type<Pair<String, String>> type1 = DSL.named(References.ITEM_NAME.typeName(), NamespacedSchema.namespacedString());
        OpticFinder<Either<Integer, Pair<String, String>>> opticfinder = DSL.fieldFinder("id", type);
        return this.fixTypeEverywhereTyped(
            "ItemIdFix",
            this.getInputSchema().getType(References.ITEM_STACK),
            this.getOutputSchema().getType(References.ITEM_STACK),
            p_15947_ -> p_15947_.update(
                    opticfinder,
                    type1,
                    p_145386_ -> p_145386_.map(
                            p_145390_ -> Pair.of(References.ITEM_NAME.typeName(), getItem(p_145390_)),
                            (Function<? super Pair<String, String>, ? extends Pair<String, String>>)(p_145388_ -> p_145388_)
                        )
                )
        );
    }
}
