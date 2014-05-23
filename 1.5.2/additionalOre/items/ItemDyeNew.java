package mods.additionalOre.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCloth;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStem;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.FakePlayerFactory;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDyeNew extends Item {

	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	public ItemDyeNew() {
		super(95);
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(CreativeTabs.tabMaterials);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, 15);
		return this.icons[j];
	}


	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
		return super.getUnlocalizedName() + "." + ItemDye.dyeColorNames[i];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.icons = new Icon[ItemDye.field_94595_b.length];

		for (int i = 0; i < ItemDye.field_94595_b.length; ++i) {
			this.icons[i] = par1IconRegister.registerIcon(ItemDye.field_94595_b[i]);
		}
		this.icons[4] = par1IconRegister.registerIcon("additionalOre:" + ItemDye.field_94595_b[4]);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int j = 0; j < 16; ++j) {
			int k;
			for(k = 0;k < par3List.size(); k++) {
				if(par3List.get(k) instanceof ItemStack) {
					if(((ItemStack)par3List.get(k)).isItemEqual(new ItemStack(par1, 1, j))) {
						par3List.remove(k);
						break;
					}
				}
			}
			par3List.add(k, new ItemStack(par1, 1, j));
		}
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving) {
		if (par2EntityLiving instanceof EntitySheep) {
			EntitySheep entitysheep = (EntitySheep)par2EntityLiving;
			int i = BlockCloth.getBlockFromDye(par1ItemStack.getItemDamage());

			if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != i) {
				entitysheep.setFleeceColor(i);
				--par1ItemStack.stackSize;
			}

			return true;
		} else {
			return false;
		}
	}

	@SideOnly(Side.CLIENT)
	public static void func_96603_a(World par0World, int par1, int par2, int par3, int par4) {
		int i1 = par0World.getBlockId(par1, par2, par3);

		if (par4 == 0) {
			par4 = 15;
		}

		Block block = i1 > 0 && i1 < Block.blocksList.length ? Block.blocksList[i1] : null;

		if (block != null) {
			block.setBlockBoundsBasedOnState(par0World, par1, par2, par3);

			for (int j1 = 0; j1 < par4; ++j1) {
				double d0 = itemRand.nextGaussian() * 0.02D;
				double d1 = itemRand.nextGaussian() * 0.02D;
				double d2 = itemRand.nextGaussian() * 0.02D;
				par0World.spawnParticle("happyVillager", (double)((float)par1 + itemRand.nextFloat()), (double)par2 + (double)itemRand.nextFloat() * block.getBlockBoundsMaxY(), (double)((float)par3 + itemRand.nextFloat()), d0, d1, d2);
			}
		} else {
			for (int j1 = 0; j1 < par4; ++j1) {
				double d0 = itemRand.nextGaussian() * 0.02D;
				double d1 = itemRand.nextGaussian() * 0.02D;
				double d2 = itemRand.nextGaussian() * 0.02D;
				par0World.spawnParticle("happyVillager", (double)((float)par1 + itemRand.nextFloat()), (double)par2 + (double)itemRand.nextFloat() * 1.0f, (double)((float)par3 + itemRand.nextFloat()), d0, d1, d2);
			}
		}
	}

	public static boolean func_96604_a(ItemStack par0ItemStack, World par1World, int par2, int par3, int par4) {
		return applyBonemeal(par0ItemStack, par1World, par2, par3, par4, FakePlayerFactory.getMinecraft(par1World));
	}

	public static boolean applyBonemeal(ItemStack par0ItemStack, World par1World, int par2, int par3, int par4, EntityPlayer player) {
		int l = par1World.getBlockId(par2, par3, par4);

		BonemealEvent event = new BonemealEvent(player, par1World, l, par2, par3, par4);
		if (MinecraftForge.EVENT_BUS.post(event)) {
			return false;
		}

		if (event.getResult() == Result.ALLOW) {
			if (!par1World.isRemote) {
				par0ItemStack.stackSize--;
			}
			return true;
		}

		if (l == Block.sapling.blockID) {
			if (!par1World.isRemote) {
				if ((double)par1World.rand.nextFloat() < 0.45D) {
					((BlockSapling)Block.sapling).markOrGrowMarked(par1World, par2, par3, par4, par1World.rand);
				}

				--par0ItemStack.stackSize;
			}

			return true;
		} else if (l != Block.mushroomBrown.blockID && l != Block.mushroomRed.blockID) {
			if (l != Block.melonStem.blockID && l != Block.pumpkinStem.blockID) {
				if (l > 0 && Block.blocksList[l] instanceof BlockCrops) {
					if (par1World.getBlockMetadata(par2, par3, par4) == 7) {
						return false;
					} else {
						if (!par1World.isRemote) {
							((BlockCrops)Block.blocksList[l]).fertilize(par1World, par2, par3, par4);
							--par0ItemStack.stackSize;
						}

						return true;
					}
				} else {
					int i1;
					int j1;
					int k1;

					if (l == Block.cocoaPlant.blockID) {
						i1 = par1World.getBlockMetadata(par2, par3, par4);
						j1 = BlockDirectional.getDirection(i1);
						k1 = BlockCocoa.func_72219_c(i1);

						if (k1 >= 2) {
							return false;
						} else {
							if (!par1World.isRemote) {
								++k1;
								par1World.setBlockMetadataWithNotify(par2, par3, par4, k1 << 2 | j1, 2);
								--par0ItemStack.stackSize;
							}

							return true;
						}
					} else if (l != Block.grass.blockID) {
						return false;
					} else {
						if (!par1World.isRemote) {
							--par0ItemStack.stackSize;
							label102:

								for (i1 = 0; i1 < 128; ++i1) {
									j1 = par2;
									k1 = par3 + 1;
									int l1 = par4;

									for (int i2 = 0; i2 < i1 / 16; ++i2) {
										j1 += itemRand.nextInt(3) - 1;
										k1 += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
										l1 += itemRand.nextInt(3) - 1;

										if (par1World.getBlockId(j1, k1 - 1, l1) != Block.grass.blockID || par1World.isBlockNormalCube(j1, k1, l1)) {
											continue label102;
										}
									}

									if (par1World.getBlockId(j1, k1, l1) == 0) {
										if (itemRand.nextInt(10) != 0) {
											if (Block.tallGrass.canBlockStay(par1World, j1, k1, l1)) {
												par1World.setBlock(j1, k1, l1, Block.tallGrass.blockID, 1, 3);
											}
										} else {
											ForgeHooks.plantGrass(par1World, j1, k1, l1);
										}
									}
								}
						}

						return true;
					}
				}
			} else if (par1World.getBlockMetadata(par2, par3, par4) == 7) {
				return false;
			} else {
				if (!par1World.isRemote) {
					((BlockStem)Block.blocksList[l]).fertilizeStem(par1World, par2, par3, par4);
					--par0ItemStack.stackSize;
				}

				return true;
			}
		} else {
			if (!par1World.isRemote) {
				if ((double)par1World.rand.nextFloat() < 0.4D) {
					((BlockMushroom)Block.blocksList[l]).fertilizeMushroom(par1World, par2, par3, par4, par1World.rand);
				}

				--par0ItemStack.stackSize;
			}

			return true;
		}
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World,
			int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
			return false;
		} else {
			if (par1ItemStack.getItemDamage() == 15) {
				if (applyBonemeal(par1ItemStack, par3World, par4, par5, par6, par2EntityPlayer)) {
					if (!par3World.isRemote) {
						par3World.playAuxSFX(2005, par4, par5, par6, 0);
					}

					return true;
				}
			} else if (par1ItemStack.getItemDamage() == 3) {
				int i1 = par3World.getBlockId(par4, par5, par6);
				int j1 = par3World.getBlockMetadata(par4, par5, par6);

				if (i1 == Block.wood.blockID && BlockLog.limitToValidMetadata(j1) == 3) {
					if (par7 == 0) {
						return false;
					}

					if (par7 == 1) {
						return false;
					}

					if (par7 == 2) {
						--par6;
					}

					if (par7 == 3) {
						++par6;
					}

					if (par7 == 4) {
						--par4;
					}

					if (par7 == 5) {
						++par4;
					}

					if (par3World.isAirBlock(par4, par5, par6)) {
						int k1 = Block.blocksList[Block.cocoaPlant.blockID].onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, 0);
						par3World.setBlock(par4, par5, par6, Block.cocoaPlant.blockID, k1, 2);

						if (!par2EntityPlayer.capabilities.isCreativeMode) {
							--par1ItemStack.stackSize;
						}
					}

					return true;
				}
			}

			return false;
		}
	}
}
