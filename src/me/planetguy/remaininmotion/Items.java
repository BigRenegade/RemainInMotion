package me.planetguy.remaininmotion ;


import org.lwjgl.opengl.GL11;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.TextureUtils;
import codechicken.microblock.MicroMaterialRegistry.IMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import codechicken.microblock.MicroblockClass;
import codechicken.microblock.MicroblockClassRegistry;
import codechicken.microblock.MicroblockClient;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class Items
{
	public static ToolItemSet ToolItemSet ;

	public static SimpleItemSet SimpleItemSet ;

	public static net.minecraft.item.Item hollowCarriage;

	public static int hollowCarriageId;

	public static void Initialize ( )
	{
		ToolItemSet = new ToolItemSet ( ) ;

		SimpleItemSet = new SimpleItemSet ( ) ;

		hollowCarriage=new FMPCarriageItem(hollowCarriageId);

		LanguageRegistry.addName(((net.minecraft.item.Item)hollowCarriage), "Hollow carriage");

		//Attempting to fix FMP crashing when trying to set creative tab
		((net.minecraft.item.Item)hollowCarriage).tabToDisplayOn=CreativeTab.Instance;

	}
}