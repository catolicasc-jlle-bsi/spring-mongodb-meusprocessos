/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.org.pucsc.meusprocessos.service;

import java.util.Date;
import java.util.List;

import br.org.pucsc.meusprocessos.model.Attachment;
import br.org.pucsc.meusprocessos.model.Expense;
import br.org.pucsc.meusprocessos.model.ExpenseType;
import br.org.pucsc.meusprocessos.model.User;

public interface ExpenseService {

	public Long createExpense(String description,ExpenseType expenseType,Date expenseDate,
			Double amount,User user,Attachment attachment);
	
	public Expense getExpense(Long expenseId);
	
	public List getAllExpenses();
	
	public List<Expense> getExpensesByUser(User user);
	
	public List<Expense> getPendingExpensesList();
	
	public List<Expense> getApprovedAndRejectedExpensesList();
	
	public Expense changeExpenseStatus(Long expenseId,String state);
	
	public void deleteExpense(Long expenseId);
	
	public void updateExpense(Long expenseId,String description,Double amount,ExpenseType expenseType);
}
