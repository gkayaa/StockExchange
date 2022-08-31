package com.example.evaexchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.example.evaexchange.dao.OwnedShareRepository;
import com.example.evaexchange.dao.PortfolioRepository;
import com.example.evaexchange.dao.ShareRepository;
import com.example.evaexchange.dao.TransactionRepository;
import com.example.evaexchange.dao.UserRepository;
import com.example.evaexchange.model.OwnedShare;
import com.example.evaexchange.model.Portfolio;
import com.example.evaexchange.model.Share;
import com.example.evaexchange.model.Transaction;
import com.example.evaexchange.model.User;

@SpringBootApplication
public class EvaExchangeApplication
{
	
	@Autowired //Autowired keyword was used to provide dependency injection. Has a similar functionality with "new" keyword but provides DI.
	private UserRepository userRepository;
	
	@Autowired
	private PortfolioRepository portfolioRepository;
	
	@Autowired
	private ShareRepository shareRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private OwnedShareRepository ownedShareRepository;
	
	public static void main(String[] args) 
	{
		
	     SpringApplication.run(EvaExchangeApplication.class, args);
	}
	
	@EventListener
	public void onReady(ApplicationReadyEvent e)
	{ //When the application is ready to be executed.
		createUsers();
		createShares();
		createTransactions();
	}
	
	private void createUsers() 
	{ //Initializing database with 5 new users.
		List<User> users = new ArrayList<>();
		users.add(new User("TestUser1", "TestUser1", 3535));
		users.add(new User("TestUser2", "TestUser2", 1997));
		users.add(new User("TestUser3", "TestUser3", 19520));
		users.add(new User("TestUser4", "TestUser4", 5000));
		users.add(new User("TestUser5", "TestUser5", 1453));
		
		List<Portfolio> portfolios = new ArrayList<Portfolio>();
		
		users.forEach(user ->
		{
			List<OwnedShare> emptyList = new ArrayList<>();
			portfolios.add(new Portfolio(user, emptyList));
		});
		
		userRepository.saveAll(users);
		portfolioRepository.saveAll(portfolios);
	}

	
	private void createShares()
	{ //Initializing database with 3 new shares.
		List<Share> shares = new ArrayList<>();
		
		shares.add(new Share("ABC", 3.14));
		shares.add(new Share("BTC", 7.49));
		shares.add(new Share("ZXY", 14.315));

		shareRepository.saveAll(shares);
	}
	
	private void createTransactions() 
	{ //Initializing database with one transaction per each user.
	
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		
		List<Share> shares = new ArrayList<>();
		shareRepository.findAll().forEach(shares::add);
	
		List<Transaction> transactions = new ArrayList<>();

		Random random = new Random();
		
		users.forEach(user -> {
			int randomIdx = random.nextInt(shares.size()); //Choose one of the shares registered in a random way.
			Share randomShare = shares.get(randomIdx);
			int quantity = random.nextInt(400) + 100; //Provide the quantity for transaction operation in a random way.
			
			Transaction transaction = new Transaction("BUY", randomShare, user, quantity); //All initial transactions are chosen to be "BUY".

			OwnedShare ownedShare = ownedShareRepository.save(new OwnedShare(randomShare, quantity));
			user.getPortfolio().getOwnedShares().add(ownedShare); //Add the bought share to the portfolio of the related user.
			portfolioRepository.save(user.getPortfolio());
			
			transactions.add(transaction);
			
		});
		
		transactionRepository.saveAll(transactions);
	}
	
}
