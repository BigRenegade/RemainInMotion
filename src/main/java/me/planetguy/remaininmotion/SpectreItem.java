package me.planetguy.remaininmotion ;

import me.planetguy.remaininmotion.base.Block;
import me.planetguy.remaininmotion.base.BlockItem;

public class SpectreItem extends BlockItem
{
	public SpectreItem ( net.minecraft.block.Block b )
	{
		super( b ) ;
	}

	@Override
	public String getItemStackDisplayName ( net . minecraft . item . ItemStack item )
	{
		return ( "Carriage Spectre" ) ;
	}

	@Override
	public void AddTooltip ( net . minecraft . item . ItemStack Item , java . util . List TooltipLines )
	{
		TooltipLines . add ( "NOT VALID FOR DIRECT USAGE" ) ;
	}
}
