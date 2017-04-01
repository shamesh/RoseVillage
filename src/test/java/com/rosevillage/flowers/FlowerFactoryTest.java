package com.rosevillage.flowers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.rosevillage.flowers.Flower;
import com.rosevillage.flowers.FlowerFactory;
import com.rosevillage.flowers.Lilies;
import com.rosevillage.flowers.Rose;

public class FlowerFactoryTest {

	FlowerFactory ff;

	@Before
	public void setup() {
		ff = new FlowerFactory();
	}

	@Test
	public void testGetFlower_Lilies() {
		Flower op = ff.getFlower("L09");
		assertTrue(op instanceof Lilies);
		assertTrue("Checking Flower name for Lilies", "Lilies".equalsIgnoreCase(op.getName()));
	}

	@Test
	public void testGetFlower_Roses() {
		Flower op = ff.getFlower("R12");
		assertEquals(Rose.class, op.getClass());
		assertTrue("Checking Flower name for Rose", "Roses".equalsIgnoreCase(op.getName()));
	}

	@Test
	public void testGetFlower_Tulip() {
		Flower op = ff.getFlower("T58");
		assertTrue("Checking Flower name for Tulip", "Tulips".equalsIgnoreCase(op.getName()));
	}

	@Test
	public void testGetFlower_NoFlower() {
		Flower op = ff.getFlower("NotAvailable");
		assertNull(op);
		assertNull(ff.getFlower(null));
	}

}
