package in.indigenous.xpense.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategorisedExpense {

	private Date date;
	private List<Expense> expenseItems = new ArrayList<Expense>();

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotal() {
		double total = 0;
		for (Expense expense : expenseItems) {
			total += expense.getAmount();
		}
		return total;
	}

	public List<Expense> getExpenseItems() {
		return expenseItems;
	}

	public void setExpenseItems(List<Expense> expenseItems) {
		this.expenseItems = expenseItems;
	}

	@Override
	public String toString() {
		return "CategorisedExpense [date=" + date + ", total=" + getTotal()
				+ ", expenseItems=" + expenseItems + "]";
	}

}
