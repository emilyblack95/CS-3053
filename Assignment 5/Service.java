package vaadin_archetype_application.Assignment5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
 * GameService class for Assignment 5 - HCI
 * 3/30/17
 * @author Emily Black
 * Game catalog interface.
 */
public class Service {

	private static Service instance;
	private static final Logger LOGGER = Logger.getLogger(Service.class.getName());

	private final HashMap<Long, Game> games = new HashMap<>();
	private long nextId = 0;

	private Service() {
	}

	public static Service getInstance() {
		if (instance == null) {
			instance = new Service();
			instance.ensureTestData();
		}
		return instance;
	}

	public synchronized List<Game> findAll() {
		return findAll(null);
	}

	public synchronized List<Game> findAll(String stringFilter) {
		ArrayList<Game> arrayList = new ArrayList<>();
		for (Game contact : games.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(contact.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Game>() {

			@Override
			public int compare(Game o1, Game o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		return arrayList;
	}

	public synchronized List<Game> findAll(String stringFilter, int start, int maxresults) {
		ArrayList<Game> arrayList = new ArrayList<>();
		for (Game contact : games.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(contact.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Game>() {

			@Override
			public int compare(Game o1, Game o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		int end = start + maxresults;
		if (end > arrayList.size()) {
			end = arrayList.size();
		}
		return arrayList.subList(start, end);
	}

	public synchronized long count() {
		return games.size();
	}

	public synchronized void delete(Game value) {
		games.remove(value.getId());
	}

	public synchronized void save(Game entry) {
		if (entry == null) {
			LOGGER.log(Level.SEVERE,
					"Game is null.");
			return;
		}
		if (entry.getId() == null) {
			entry.setId(nextId++);
		}
		try {
			entry = (Game) entry.clone();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		games.put(entry.getId(), entry);
	}

	public void ensureTestData() {
		if (findAll().isEmpty()) {
			
			final String[] games = new String[] { "Mass Effect: Andromeda", "Minecraft", "Grand Theft Auto V",
					"Destiny", "Battlefield 1", "World of Warcraft", "Portal", "Resident Evil 7: Biohazard",
					"Pac-Man", "Call of Duty: Infinite Warfare", "Uncharted 4: A Thief's End", "For Honor", "Overwatch",
					"Tom Clancy's The Division", "Dark Souls III", "Injustice 2", "Pokémon GO", "Nier: Automata",
					"Fallout 4", "The Legend of Zelda", "Tetris", "Halo Wars 2", "The Elder Scrolls V: Skyrim",
					"Mario Kart 8", "Counter-Strike", "Batman: The Telltale Series", "Watch Dogs 2", "Counter-Strike: Global Offensive",
					"Life Is Strange", "The Witcher 3: Wild Hunt" };
			final String[] developers = new String[] { "BioWare", "Mojang, 4J Studios", "Rockstar Games", "Bungie, High Moon Studios, Radical Entertainment",
					"EA DICE", "Blizzard Entertainment", "Valve Corporation", "Capcom", "Namco, Atari, Interactive Brains", "Infinity Ward",
					"Naughty Dog", "Ubisoft", "Blizzard Entertainment", "Massive Entertainment, Red Storm Entertainment, Ubisoft",
					"FromSoftware", "NetherRealm Studios", "Niantic", "Platinum Games", "Bethesda Game Studios", "Nintendo", "Philips",
					"343 Industries, Creative Assembly", "Bethesda Game Studios", "Nintendo", "Valve Corporation, Turtle Rock Studios",
					"Telltale Games", "Ubisoft", "Valve Corporation", "Dontnod Entertainment", "CD Projekt RED" };
			final String[] years = new String[] { "2017", "2011", "2013", "2014", "2016", "2004", "2007", "2017", "1980", "2016", "2016", "2017", 
					"2016", "2016", "2016", "2017", "2016", "2017", "2015", "1986", "1984", "2017", "2011", "2014", "2000", "2016", "2016",
					"2012", "2015", "2015" };
			
			for(int i = 0; i < games.length; i++)
			{
				Random r = new Random(0);
				Game c = new Game();
				c.setTitle(games[i]);
				c.setDeveloper(developers[i]);
				c.setYear(years[i]);
				c.setStatus(Genre.values()[r.nextInt(Genre.values().length)]); //randomly sets genre
				save(c);
			}
		}
	}
}