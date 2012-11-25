package com.podts.ancientforge;

import java.util.ArrayList;
 
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.NBTTagString;
 
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
 
public class ItemNamer {
    
    public static void setName(ItemStack item, String name) {
    	
    	net.minecraft.server.ItemStack stack = new CraftItemStack(item).getHandle();
    	
        NBTTagCompound tag = stack.tag;
        if (tag == null) {
        	System.out.println("creating compund.");
        	tag = new NBTTagCompound();
            tag.setCompound("display", new NBTTagCompound());
            stack.tag = tag;
        }
    	
        tag = stack.tag.getCompound("display");
        tag.setString("Name", name);
        stack.tag.setCompound("display", tag);
        
        
        
    }
    
    public static String getName(ItemStack item) {
    	
    	net.minecraft.server.ItemStack stack = new CraftItemStack(item).getHandle();
    	
        NBTTagCompound tag = stack.tag.getCompound("display");
        if (tag == null) {
            return item.toString();
        }
        return tag.getString("Name");
    }
   
    public static void addLore(ItemStack item, String lore) {
    	
    	net.minecraft.server.ItemStack stack = new CraftItemStack(item).getHandle();
    	
        NBTTagCompound tag = stack.tag.getCompound("display");
        NBTTagList list = tag.getList("Lore");
        if (list == null) list = new NBTTagList();
        list.add(new NBTTagString(lore));
        tag.set("Lore", list);
        stack.tag.setCompound("display", tag);
        
    }
   
    public static void setLore(ItemStack item, String lore) {
    	
    	net.minecraft.server.ItemStack stack = new CraftItemStack(item).getHandle();
    	
        NBTTagCompound tag = stack.tag.getCompound("display");
        NBTTagList list = new NBTTagList();
        list.add(new NBTTagString(lore));
        tag.set("Lore", list);
        stack.tag.setCompound("display", tag);
    }
   
    public static String[] getLore(ItemStack item) {
    	
    	net.minecraft.server.ItemStack stack = new CraftItemStack(item).getHandle();
    	
        NBTTagCompound tag = stack.tag;
        NBTTagList list = tag.getCompound("display").getList("Lore");
        ArrayList<String> strings = new ArrayList<String>();
        String[] lores = new String[] {};
        for (int i = 0; i < strings.size(); i++)
            strings.add(((NBTTagString) list.get(i)).data);
        strings.toArray(lores);
        return lores;
    }
    
    public static boolean isverifyed(ItemStack item) {
    	
    	net.minecraft.server.ItemStack stack = new CraftItemStack(item).getHandle();
    	NBTTagCompound tag = stack.tag.getCompound("display");
        if (tag == null)
            return false;
        
    	return tag.getBoolean("verify");
    }
    
    public static void verify(ItemStack item) {
    	
    	net.minecraft.server.ItemStack stack = new CraftItemStack(item).getHandle();
    	
    	NBTTagCompound tag = stack.tag;
        if (tag == null) {
            tag = new NBTTagCompound();
            tag.setCompound("display", new NBTTagCompound());
            stack.tag = tag;
        }
        
        tag.setBoolean("verify", true);
        stack.tag.setCompound("display", tag);
        
    }
    
    public static void unverify(ItemStack item) {
    	
    	net.minecraft.server.ItemStack stack = new CraftItemStack(item).getHandle();
    	
    	NBTTagCompound tag = stack.tag.getCompound("display");
        tag.setBoolean("verify", false);
        stack.tag.setCompound("display", tag);
    }
}
