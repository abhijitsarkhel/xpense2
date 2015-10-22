package in.indigenous.xpense.test;

import in.indigenous.xpense.model.CategorisedExpense;
import in.indigenous.xpense.model.Category;
import in.indigenous.xpense.model.Expense;
import in.indigenous.xpense.model.SubCategory;
import in.indigenous.xpense.utils.XMLParser;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLParserTest {

	public static void main(String[] args) {

		XMLParser<Expense> expenseParser = new XMLParser<Expense>();
		File file = new File("./testdata/expenses.xml");
		List<Expense> expenses = expenseParser.parse(file, Expense.class);
		System.out.println(expenses);

		XMLParser<Category> categoryParser = new XMLParser<Category>();
		file = new File("./testdata/categories.xml");
		List<Category> categories = categoryParser.parse(file, Category.class);
		System.out.println(categories);

		XMLParser<SubCategory> subCategoryParser = new XMLParser<SubCategory>();
		file = new File("./testdata/subCategories.xml");
		List<SubCategory> subCategories = subCategoryParser.parse(file, SubCategory.class);
		System.out.println(subCategories);

		Map<Date, CategorisedExpense> categorisedExpenses = new HashMap<Date, CategorisedExpense>();
		for(Expense expense: expenses)
		{

			Calendar cal = Calendar.getInstance();
			cal.setTime(expense.getCreated());
			int dat = cal.get(Calendar.DATE);
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			Calendar cal2 = Calendar.getInstance();
			cal2.set(year, month, dat, 0, 0, 0);
			Date date = cal2.getTime();

			CategorisedExpense categorisedExpense = categorisedExpenses.get(date);
			if(categorisedExpense == null)
			{
				categorisedExpense = new CategorisedExpense();
				categorisedExpense.setDate(date);
				categorisedExpense.getExpenseItems().add(expense);
				categorisedExpenses.put(date, categorisedExpense);
			}
			else
			{
				categorisedExpense.getExpenseItems().add(expense);
			}
		}
		System.out.println("Categorised Expense Map :: " + categorisedExpenses);
	}

}
