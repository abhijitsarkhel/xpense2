package in.indigenous.xpense.model;

import java.util.Date;

public class Expense {

	private int categoryId;
	private int subCategoryId;
	private double amount;
	private Date created;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Expense [categoryId=" + categoryId + ", subCategoryId="
				+ subCategoryId + ", amount=" + amount + ", created=" + created
				+ "]";
	}


}
