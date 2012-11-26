package com.podts.ancientforge;

import java.util.ArrayList;

import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.NBTTagString;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import com.podts.ancientforge.namemodifier.ItemPrefix;
import com.podts.ancientforge.namemodifier.ItemSuffix;


public class NamedItem {
	
	public static boolean isPluginItem(CraftItemStack item) {
		
		if (item == null)
			return false;
		
		net.minecraft.server.ItemStack stack = item.getHandle();
		
		NBTTagCompound tag = stack.tag;
		
		if (tag == null)
			return false;
		
        tag = stack.tag.getCompound("display");
        
        if (tag == null)
        	return false;
        
        return tag.getBoolean("verify");
		
	}
	
	private net.minecraft.server.ItemStack mcstack;
	
	private CraftItemStack itemstack;
	
	private int slot;
	
	public int getSlot() {
		return slot;
	}
	
	public void setSlot(int slot) {
		this.slot = slot;
	}
	
	public ItemStack getItemStack() {
		return itemstack;
	}
	
	public boolean hasPrefix() {
		
		for (ItemPrefix prefix : ItemPrefix.getPrefixs().values()) {
			
			if (getName().startsWith(prefix.getName()))
				return true;
			
		}
		
		return false;
		
	}
	
	public boolean hasSuffix() {
		
		for (ItemSuffix suffix : ItemSuffix.getSuffixs().values()) {
			
			if (getName().endsWith(suffix.getName()))
				return true;
			
		}
		
		return false;
		
	}
	
	public boolean hasName() {
		
		if (getFullName().equals(""))
			return false;
		return true;
		
	}
	
	public boolean isVerifyed() {
		
		NBTTagCompound tag = mcstack.tag;
        return tag.getCompound("display").getBoolean("verify");
        
	}
	
	public void verify() {
		
		NBTTagCompound tag = mcstack.tag;
        tag = mcstack.tag.getCompound("display");
        tag.setBoolean("verify", true);
        mcstack.tag.setCompound("display", tag);
		
	}
	
	public void unverify() {
		
		NBTTagCompound tag = mcstack.tag;
        tag = mcstack.tag.getCompound("display");
        tag.setBoolean("verify", false);
        mcstack.tag.setCompound("display", tag);
		
	}
	
	public String getFullName() {
		
		NBTTagCompound tag = mcstack.tag;
        return tag.getCompound("display").getString("Name");
		
	}
	
	public String getName() {
		
        NBTTagCompound tag = mcstack.tag;
        String normal = tag.getCompound("display").getString("Name");
        
        return normal.substring(ChatColor.RESET.toString().length());
        
	}
	
	public String getStrippedName() {
		return ChatColor.stripColor(getName());
	}
	
	public void setName(String name) {
    	
		String realname = name.replaceAll("&", "§");
		
        NBTTagCompound tag = mcstack.tag;
        tag = mcstack.tag.getCompound("display");
        tag.setString("Name", ChatColor.RESET + realname);
        mcstack.tag.setCompound("display", tag);
        verify();
		
	}
	
	public void addLore(String lore) {
		    	
		NBTTagCompound tag = mcstack.tag.getCompound("display");
		NBTTagList list = tag.getList("Lore");
		if (list == null) list = new NBTTagList();
		list.add(new NBTTagString("",lore));
		tag.set("Lore", list);
		mcstack.tag.setCompound("display", tag);
	    
	}
	
	public void setLore(String lore) {
	
		NBTTagCompound tag = mcstack.tag.getCompound("display");
		NBTTagList list = new NBTTagList();
		list.add(new NBTTagString("",lore));
		tag.set("Lore", list);
		mcstack.tag.setCompound("display", tag);
		
	}
	
	public void setLore(String lore, int index) {
		
		NBTTagCompound tag = mcstack.tag.getCompound("display");
		NBTTagList list = tag.getList("Lore");
		if (list == null) list = new NBTTagList();
		if (list.get(index) == null)
			return;
		list.get(index).setName(lore);
		tag.set("Lore", list);
		mcstack.tag.setCompound("display", tag);
		
	}
	
	public int getLoreSize() {
		
		NBTTagCompound tag = mcstack.tag.getCompound("display");
		NBTTagList list = tag.getList("Lore");
		if (list == null) list = new NBTTagList();
		return list.size();
		
	}
	
	public void setLoreSize(int size) {
		
		NBTTagCompound tag = mcstack.tag.getCompound("display");
		NBTTagList list = tag.getList("Lore");
		if (list == null) list = new NBTTagList();
		
		tag.set("Lore", list);
		mcstack.tag.setCompound("display", tag);
		
	}
	
	public String getLore(int line) {
		
		NBTTagCompound tag = mcstack.tag.getCompound("display");
		NBTTagList list = tag.getList("Lore");
		if (list == null) list = new NBTTagList();
		return list.get(line).getName();
		
	}
	
	public String[] getLore() {
		    	
		NBTTagCompound tag = mcstack.tag;
		NBTTagList list = tag.getCompound("display").getList("Lore");
		ArrayList<String> strings = new ArrayList<String>();
		String[] lores = new String[] {};
		for (int i = 0; i < strings.size(); i++)
			strings.add(((NBTTagString) list.get(i)).data);
		strings.toArray(lores);
		return lores;
		
	}
	
	public String[] getStrippedLore() {
		
		String[] lore = getLore();
		
		for (int i=0; i<lore.length; i++) {
			
			lore[i] = ChatColor.stripColor(lore[i]);
			
		}
		
		return lore;
		
	}
	
	public NamedItem(ItemStack item) {
		
		itemstack = (CraftItemStack) item;
		
		net.minecraft.server.ItemStack stack = itemstack.getHandle();
        NBTTagCompound tag = stack.tag;
        if (tag == null) {
        	System.out.println("created tag compound.");
        	tag = new NBTTagCompound();
            tag.setCompound("display", new NBTTagCompound());
            stack.tag = tag;
        }
        
        mcstack = stack;
		
	}
	
	public NamedItem(ItemStack item, String name) {
		
		itemstack = (CraftItemStack) item;
		
		net.minecraft.server.ItemStack stack = itemstack.getHandle();
        NBTTagCompound tag = stack.tag;
        if (tag == null) {
        	tag = new NBTTagCompound();
            tag.setCompound("display", new NBTTagCompound());
            stack.tag = tag;
        }
        
        mcstack = stack;
        
        setName(name);
		
	}
	
	
}
