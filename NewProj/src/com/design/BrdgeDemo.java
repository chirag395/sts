package com.design;

interface BrandType
{
	void anyBrandType();
	
}

abstract class Brands
{
	protected BrandType brand1;
	protected BrandType brand2;
	
	public Brands(BrandType type1, BrandType type2)
	{
		this.brand1 = type1;
		this.brand2 = type2;
	}
	
	public abstract void designCompany();
}

class OriginalBrand implements BrandType
{

	@Override
	public void anyBrandType() {
		
		System.out.println("original");
	}	
}

class CopyBrand implements BrandType
{

	@Override
	public void anyBrandType() {
		
		System.out.println("Clone");
		
	}	
}

class AppleB1 extends Brands
{

	public AppleB1(BrandType type1, BrandType type2) {
		super(type1, type2);
		
	}

	@Override
	public void designCompany() {
		
		System.out.println("APPLE INDIA");
		
		brand1.anyBrandType();
		brand2.anyBrandType();
	}
	
}

class DellB2 extends Brands
{

	public DellB2(BrandType type1, BrandType type2) {
		super(type1, type2);
	}

	@Override
	public void designCompany() {
		
		System.out.println("DELL INDIA");
		
		brand1.anyBrandType();
		brand2.anyBrandType();
		
	}	
}

class ProductStatus implements BrandType
{

	@Override
	public void anyBrandType() {
		
		System.out.println("Completed");
	}	
}

class ProductDelivered implements BrandType
{

	@Override
	public void anyBrandType() {
		
		System.out.println("Delivered.");
	}
	
}

public class BrdgeDemo {
	
	public static void main(String[] args) {
		
		Brands obj1 = new AppleB1(new ProductStatus(), new ProductDelivered());
		
		Brands obj2 = new DellB2(new ProductStatus(), new ProductDelivered());
		
		obj1.designCompany();
		obj2.designCompany();
	}
}
