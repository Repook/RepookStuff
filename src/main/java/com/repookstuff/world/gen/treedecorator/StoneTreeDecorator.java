package com.repookstuff.world.gen.treedecorator;

import com.repookstuff.ExampleMod;
import com.repookstuff.item.ModItems;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StoneTreeDecorator extends TreeDecorator {
    private static final Direction STONE_FACE = Direction.SOUTH;
    private static final Direction[] GENERATE_DIRECTIONS = (Direction[])Direction.Type.HORIZONTAL.stream().filter(direction -> direction != STONE_FACE.getOpposite()).toArray(Direction[]::new);
    private final float probability;

    public StoneTreeDecorator(float probability) {
        this.probability = probability;
    }
    @Override
    protected TreeDecoratorType<?> getType() {
        return TreeDecoratorType.BEEHIVE;
    }

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        if (random.nextFloat() >= this.probability) {
            return;
        }
        ObjectArrayList<BlockPos> list = generator.getLeavesPositions();
        ObjectArrayList<BlockPos> list2 = generator.getLogPositions();
        int i = !list.isEmpty() ? Math.max(((BlockPos)list.get(0)).getY() - 1, ((BlockPos)list2.get(0)).getY() + 1) : Math.min(((BlockPos)list2.get(0)).getY() + 1 + random.nextInt(3), ((BlockPos)list2.get(list2.size() - 1)).getY());
        List list3 = list2.stream().filter(pos -> pos.getY() == i).flatMap(pos -> Stream.of(GENERATE_DIRECTIONS).map(pos::offset)).collect(Collectors.toList());
        if (list3.isEmpty()) {
            return;
        }
        Collections.shuffle(list3);
        Optional<BlockPos> optional = list3.stream().filter(pos -> generator.isAir((BlockPos)pos) && generator.isAir(((BlockPos) pos).offset(STONE_FACE))).findFirst();
        if (optional.isEmpty()) {
            return;
        }
        generator.replace(optional.get(), (BlockState) Blocks.DIAMOND_BLOCK.getDefaultState());
    }
    public static void registerModTreeDecorators(){
        ExampleMod.LOGGER.info("Registering Tree Decorators for"+ExampleMod.MOD_ID);
    }
    }

