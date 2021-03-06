package me.planetguy.remaininmotion;

import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import me.planetguy.remaininmotion.core.Blocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import codechicken.lib.lighting.LightMatrix;
import codechicken.lib.render.CCModel;
import codechicken.lib.render.Vertex5;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.microblock.IMicroMaterialRender;
import codechicken.microblock.MicroMaterialRegistry;
import codechicken.microblock.MicroMaterialRegistry.IMicroMaterial;
import codechicken.microblock.MicroblockRender;
import codechicken.multipart.JCuboidPart;
import codechicken.multipart.JIconHitEffects;
import codechicken.multipart.JNormalOcclusion;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.minecraft.McBlockPart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.common.Optional;

public class FMPCarriage extends McBlockPart implements JNormalOcclusion{

	public static FMPCarriage instance;

	final Renderer renderer=new Renderer();

	static final double l=1.5/8;

	public static final Cuboid6[] cubeOutsideEdges=new Cuboid6[]{ //not most efficient, but understandable, system;
		new Cuboid6(0,0,0, 1, l, l),
		new Cuboid6(0,0,0, l, 1-l, l),
		new Cuboid6(0,0,0, l, l, 1),

		new Cuboid6(1,1,0, 0,   1-l, l ),
		new Cuboid6(1,1,0, 1-l, l,   l ),
		new Cuboid6(1,1,0, 1-l, 1-l, 1-l),

		new Cuboid6(1,0,1, 1-l, l,    l ),
		new Cuboid6(1,0,1, 1-l, 1-l,  1-l),
		new Cuboid6(1,0,1, l,   l,    1-l),

		new Cuboid6(0,1,1, 1,  1-l, 1-l),
		new Cuboid6(0,1,1, l,  l,   1-l),
		new Cuboid6(0,1,1, l,  1-l, l),

	};

	@Override
	public Iterable<Cuboid6> getOcclusionBoxes() {
		return new Iterable(){
			public Iterator iterator(){
				return new Iterator(){
					//empty iterator
					@Override
					public boolean hasNext() {return false;}
					@Override
					public Object next() {return null;}
					@Override
					public void remove() {}
				};
				
			}
		};
	}

	public Iterable<Cuboid6> getCollisionBoxes() {
		return new Iterable(){

			@Override
			public Iterator iterator() {
				return new Iterator(){

					int idx=-1;

					@Override
					public boolean hasNext() {
						return idx+1<cubeOutsideEdges.length;
					}

					@Override
					public Object next() {
						idx++;
						return cubeOutsideEdges[idx];
					}

					@Override
					public void remove() {
					}

				};
			}

		};
	}

	@Override
	public String getType() {
		return "FMPCarriage";
	}

	public Cuboid6 getBounds(){
		return Cuboid6.full;
	}


	@Override
	public Block getBlock() {
		return Blocks.Carriage;
	}

	@Override
	public boolean renderStatic(Vector3 pos, int pass){
		renderer.renderCovers(this.world(), pos, null, pass);
		return true;
	}

	@SideOnly(Side.CLIENT)
	private static class Renderer implements IMicroMaterialRender{

		BlockCoord pos=new BlockCoord();

		public CCModel generateModel(){
			CCModel ccm=CCModel.quadModel(12*8);
			for(int i=0; i<12; i++){
				ccm.generateBlock(i*8, cubeOutsideEdges[i]);
			}
			return ccm;
		}

		private World world;

		@Override
		public Cuboid6 getRenderBounds() {
			return Cuboid6.full;
		}

		@Override
		public World world() {
			return world;
		}

		@Override
		public int x() {
			return pos.x;
		}

		@Override
		public int y() {
			return pos.y;
		}

		@Override
		public int z() {
			return pos.z;
		}

		public void renderCovers(World world, Vector3 t, LightMatrix olm, int material){
			IMicroMaterial microMaterial = MicroMaterialRegistry.getMaterial("tile.wood");
			for(Cuboid6 c:cubeOutsideEdges){
				MicroblockRender.renderCuboid(t, microMaterial,0, c, 0);
			}
		}

	}

}
