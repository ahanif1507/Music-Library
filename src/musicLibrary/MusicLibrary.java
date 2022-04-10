package musicLibrary;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MusicLibrary {

	private HashSet<Song> musicLibrary = new HashSet<Song>();

	private ArrayList<Song> musicLibraryWriter = new ArrayList<Song>();

	private HashMap<String, ArrayList<Song>> artistsMap = new HashMap<String, ArrayList<Song>>();
	private HashMap<String, ArrayList<Song>> genresMap = new HashMap<String, ArrayList<Song>>();
	private HashMap<String, ArrayList<Song>> playlistMap = new HashMap<String, ArrayList<Song>>();
	private HashMap<String, ArrayList<Song>> titlesMap = new HashMap<String, ArrayList<Song>>();
	private HashMap<String, ArrayList<Song>> albumsMap = new HashMap<String, ArrayList<Song>>();

	/**
	 * 
	 * Add a song to the music library. Throws the SongAlreadyExistsException if the
	 * song to be added already exists in the library.
	 * 
	 * This method has no specific efficiency requirements.
	 * 
	 * @param artist    The artist of the song
	 * @param title     The title of the song
	 * @param album     The album the song is a part of
	 * @param genre     The genre of the song
	 * @param playlists The array of playlists this song is on. null if the song is
	 *                  not on any playlists.
	 * @throws SongAlreadyExistsException when the song already exists in the
	 *                                    library.
	 */
	public void addSong(String artist, String title, String album, String genre, 
						String[] playlists) throws SongAlreadyExistsException {

		Song s = new Song(artist, title, album, genre);

		ArrayList<Song> songSet = new ArrayList<Song>();

		if (musicLibrary.contains(s)) {
			throw new SongAlreadyExistsException();
		} else {
			if (playlists == null) {

				musicLibrary.add(s);

				musicLibraryWriter.add(s);

				songSet.add(s);

				// artists HashMap
				if (artistsMap.containsKey(artist)) {
					ArrayList<Song> artistSet = 
							new ArrayList<Song>(artistsMap.get(artist));
					artistSet.add(s);
					artistsMap.put(artist, artistSet);
				} else {
					artistsMap.put(artist, songSet);
				}

				// genres HashMap
				if (genresMap.containsKey(genre)) {
					ArrayList<Song> genreSet = 
							new ArrayList<Song>(genresMap.get(genre));
					genreSet.add(s);
					genresMap.put(genre, genreSet);
				} else {
					genresMap.put(genre, songSet);
				}

				// titles HashMap
				if (titlesMap.containsKey(title)) {
					ArrayList<Song> titleSet = 
							new ArrayList<Song>(titlesMap.get(title));
					titleSet.add(s);
					titlesMap.put(title, titleSet);
				} else {
					titlesMap.put(title, songSet);
				}

				// albums HashMap
				if (albumsMap.containsKey(album)) {
					ArrayList<Song> albumSet = 
							new ArrayList<Song>(albumsMap.get(album));
					albumSet.add(s);
					albumsMap.put(album, albumSet);

				} else {
					albumsMap.put(album, songSet);
				}

			} else {

				String str = genre;

				for (int i = 0; i < playlists.length; i++) {
					str = str + ";" + playlists[i];
				}

				Song s2 = new Song(artist, title, album, str);

				musicLibrary.add(s2);

				musicLibraryWriter.add(s2);

				songSet.add(s2);

				// playlists HashMap
				for (int i = 0; i < playlists.length; i++) {
					if (playlistMap.containsKey(playlists[i])) {
						ArrayList<Song> playlistSetValue = 
								new ArrayList<Song>(playlistMap.get(playlists[i]));
						playlistSetValue.add(s2);
						playlistMap.put(playlists[i], playlistSetValue);

					} else {
						playlistMap.put(playlists[i], songSet);
					}
				}

				// artists HashMap
				if (artistsMap.containsKey(artist)) {
					ArrayList<Song> artistSet = 
							new ArrayList<Song>(artistsMap.get(artist));
					artistSet.add(s2);
					artistsMap.put(artist, artistSet);
				} else {
					artistsMap.put(artist, songSet);
				}

				// genres HashMap
				if (genresMap.containsKey(genre)) {
					ArrayList<Song> genreSet = 
							new ArrayList<Song>(genresMap.get(genre));
					genreSet.add(s2);
					genresMap.put(genre, genreSet);
				} else {
					genresMap.put(genre, songSet);
				}

				if (titlesMap.containsKey(title)) {
					ArrayList<Song> titleSet = 
							new ArrayList<Song>(titlesMap.get(title));
					titleSet.add(s);
					titlesMap.put(title, titleSet);
				} else {
					titlesMap.put(title, songSet);
				}

				// albums HashMap
				if (albumsMap.containsKey(album)) {
					ArrayList<Song> albumSet = 
							new ArrayList<Song>(albumsMap.get(album));
					albumSet.add(s);
					albumsMap.put(album, albumSet);

				} else {
					albumsMap.put(album, songSet);
				}

			}
		}
	}

	/**
	 * Return all of the songs in the library
	 * 
	 * This must be O(N), where N is the number of songs in the library.
	 * 
	 * @return Array of songs
	 */
	public Song[] getAllSongs() {
		Song[] allSongs = musicLibraryWriter.toArray(new Song[0]);
		return allSongs;
	}

	/**
	 * Input the music database from the file specified by filename. If the file
	 * does not exist, then add nothing to the library.
	 * 
	 * This method has no specific efficiency requirements, but in order to
	 * confidently pass Autolab you should be able to load 250,000 songs in less
	 * than 15 seconds.
	 * 
	 * @param filename The file to load the music from
	 */
	public void loadMusicDb(String filename) {

		FileReader fr;
		try {
			fr = new FileReader(filename);
		} catch (Exception e) {
			System.out.println("Error: Could not open the file");
			return;
		}
		Scanner read = new Scanner(fr);
		while (read.hasNextLine()) {
			String[] song = new String[4];

			for (int i = 0; i < 4; i++) {
				song[i] = read.nextLine();
			}

			String[] s = song[3].split(";");

			if (s.length < 2) {
				try {
					addSong(song[0], song[1], song[2], s[0], null);
				} catch (SongAlreadyExistsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				String[] arr = new String[s.length - 1];
				for (int i = 1; i < s.length; i++) {
					arr[i - 1] = s[i];
				}
				try {
					addSong(song[0], song[1], song[2], s[0], arr);
				} catch (SongAlreadyExistsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (read.hasNextLine()) {
				read.nextLine();
			}

		}
		read.close();

		return;
	}

	/**
	 * Return all of the artists in the library without duplicates
	 * 
	 * This must be O(M), where M is the number of artists.
	 * 
	 * @return Array of artist names
	 */
	public String[] getAllArtists() {

		ArrayList<String> keys = new ArrayList<String>(artistsMap.keySet());

		String[] artists = keys.toArray(new String[0]);

		Arrays.sort(artists);

		return artists;
	}

	/**
	 * Retrieve all of the songs by a given artist and return them
	 * 
	 * This must be O(M), where M is the number of songs by that artist.
	 * 
	 * @param artist The artist to search for
	 * @return An array of songs by the given artist. If the artist is not in the
	 *         MusicLibrary, return an empty array.
	 */
	public Song[] getSongsByArtist(String artist) {

		if (artistsMap.containsKey(artist)) {

			ArrayList<Song> value = new ArrayList<Song>(artistsMap.get(artist));

			Song[] songs = value.toArray(new Song[0]);

			return songs;
		} else {
			return new Song[0];
		}
	}

	/**
	 * Return all of the titles in the library without duplicates
	 * 
	 * This must be O(M), where M is the number of titles.
	 * 
	 * @return Array of title names
	 */
	public String[] getTitles() {

		ArrayList<String> keys = new ArrayList<String>(titlesMap.keySet());
		String[] titles = keys.toArray(new String[0]);
		Arrays.sort(titles);

		return titles;
	}

	/**
	 * Retrieve all of the songs by a given title and return them
	 * 
	 * This must be O(M), where M is the number of songs by that title.
	 * 
	 * @param title The title to search for
	 * @return An array of songs by the given title. If the title is not in the
	 *         MusicLibrary, return an empty array.
	 */
	public Song[] getSongsByTitle(String title) {

		if (titlesMap.containsKey(title)) {
			ArrayList<Song> value = new ArrayList<Song>(titlesMap.get(title));
			Song[] songs = value.toArray(new Song[0]);
			return songs;
		} else {
			return new Song[0];
		}
	}
	
	/*
	public Song[] getSongsSortedByTitle() {

		if (getAllSongs().length > 0) {

			Song[] allSongs = getAllSongs();

			HashSet<String> title = new HashSet<String>();

			for (int i = 0; i < allSongs.length; i++) {
				title.add(allSongs[i].getTitle());
			}

			String[] titles = title.toArray(new String[0]);

			Arrays.sort(titles);

			ArrayList<Song> songs = new ArrayList<Song>();

			for (int i = 0; i < titles.length; i++) {
				for (int j = 0; j < allSongs.length; j++) {
					if (allSongs[j].getTitle().equals(titles[i])) {
						songs.add(allSongs[j]);
					}
				}
			}

			Song[] res = songs.toArray(new Song[0]);
			return res;

		} else {
			return new Song[0];
		}
	}
	*/

	/**
	 * Return all of the albums in the library without duplicates
	 * 
	 * This must be O(M), where M is the number of albums.
	 * 
	 * @return Array of album names
	 */
	public String[] getAlbums() {

		ArrayList<String> keys = new ArrayList<String>(albumsMap.keySet());
		String[] albums = keys.toArray(new String[0]);
		Arrays.sort(albums);

		return albums;
	}

	/**
	 * Retrieve all of the songs by a given album and return them
	 * 
	 * This must be O(M), where M is the number of songs by that album.
	 * 
	 * @param album The album to search for
	 * @return An array of songs by the given album. If the album is not in the
	 *         MusicLibrary, return an empty array.
	 */
	public Song[] getSongsByAlbum(String album) {

		if (albumsMap.containsKey(album)) {
			ArrayList<Song> value = new ArrayList<Song>(albumsMap.get(album));
			Song[] songs = value.toArray(new Song[0]);
			return songs;
		} else {
			return new Song[0];
		}
	}
	
	/*
	public Song[] getSongsSortedByAlbum() {

		if (getAllSongs().length > 0) {

			Song[] allSongs = getAllSongs();

			HashSet<String> album = new HashSet<String>();

			for (int i = 0; i < allSongs.length; i++) {
				album.add(allSongs[i].getAlbum());
			}

			String[] albums = album.toArray(new String[0]);

			Arrays.sort(albums);

			ArrayList<Song> songs = new ArrayList<Song>();

			for (int i = 0; i < albums.length; i++) {
				for (int j = 0; j < allSongs.length; j++) {
					if (allSongs[j].getAlbum().equals(albums[i])) {
						songs.add(allSongs[j]);
					}
				}
			}

			Song[] res = songs.toArray(new Song[0]);
			return res;

		} else {
			return new Song[0];
		}
	}
	*/

	/**
	 * Retrieve all of the genres in the library without duplicates
	 * 
	 * This must be O(M), where M is the number of genres.
	 * 
	 * @return An array of strings, with each string being a genre
	 */
	public String[] getGenres() {

		ArrayList<String> keys = new ArrayList<String>(genresMap.keySet());

		String[] genres = keys.toArray(new String[0]);

		Arrays.sort(genres);

		return genres;
	}

	/**
	 * Retrieve all of the songs with a given genre and return them
	 * 
	 * This must be O(M), where M is the songs within the specified genre.
	 * 
	 * @param genre The genre
	 * @return An array of songs by with the given genre. If there are no songs with
	 *         that genre, return an empty array.
	 */
	public Song[] getSongsByGenre(String genre) {

		if (genresMap.containsKey(genre)) {

			ArrayList<Song> value = new ArrayList<Song>(genresMap.get(genre));

			Song[] songs = value.toArray(new Song[0]);

			return songs;
		} else {
			return new Song[0];
		}
	}

	/**
	 * Create a new playlist with the specified name, adding it to the list of
	 * playlists. If a playlist with that name already exists, do not create another
	 * one.
	 * 
	 * This method has no specific efficiency requirements, but be reasonable.
	 * 
	 * @param playlistName The name of the playlist to create
	 */
	public void createPlaylist(String playlistName) {
		playlistMap.put(playlistName, null);
		return;
	}

	/**
	 * 
	 * Add all songs with the specified title to the specified playlist. If the
	 * playlist does not already exist, throw the appropriate exception.
	 * 
	 * @param title        Title of song to add to the playlist
	 * @param playlistName The name of the playlist to add the some to
	 * @throws PlaylistNotFoundException when the playlist specified does not exist
	 */
	public void addSongToPlaylist(String title, String playlistName) 
				throws PlaylistNotFoundException {

		if (playlistMap.containsKey(playlistName)) {

			ArrayList<Song> song = new ArrayList<Song>();

			Song[] songs = this.getAllSongs();

			for (int i = 0; i < songs.length; i++) {
				if (songs[i].getTitle().equals(title)) {
					songs[i].addToPlaylist(playlistName);
					song.add(songs[i]);
				}
			}

			playlistMap.put(playlistName, song);
		} else {
			throw new PlaylistNotFoundException();
		}
	}

	/**
	 * Return all of the playlists in the library without duplicates
	 * 
	 * This must be O(M), where M is the number of playlists.
	 * 
	 * @return Array of playlist names
	 */
	public String[] getPlaylists() {
		ArrayList<String> keys = new ArrayList<String>(playlistMap.keySet());

		String[] playlists = keys.toArray(new String[0]);

		Arrays.sort(playlists);

		return playlists;
	}

	/**
	 * 
	 * Return an array of all the songs in a playlist. If the playlist does not
	 * exist, throw the appropriate exception.
	 * 
	 * This must be O(M), where M is the number of songs in the playlist.
	 * 
	 * @param playlist The name of the playlist
	 * @return An array of songs in the given playlist. If there are no songs in the
	 *         playlist, return an empty array.
	 * @throws PlaylistNotFoundException when the playlist does not exist
	 */
	public Song[] getSongsByPlaylist(String playlist) throws PlaylistNotFoundException {

		if (playlistMap.containsKey(playlist)) {
			if (playlistMap.get(playlist) != null) {
				ArrayList<Song> value = new ArrayList<Song>(playlistMap.get(playlist));
				Song[] songs = value.toArray(new Song[0]);
				return songs;
			} else {
				return new Song[0];
			}
		} else {
			throw new PlaylistNotFoundException();
		}
	}

	/**
	 * Write the music database to the file specified by filename. The music DB must
	 * be output using the same format as the input. (So, you must be able to input
	 * a file that you output.)
	 * 
	 * @param filename The file to write the music to
	 */
	public void writeMusicDb(String filename) {
		PrintWriter out;
		try {
			out = new PrintWriter(filename);
		} catch (Exception e) {
			System.out.println("Error: Could not open the file");
			return;
		}

		Song[] s = musicLibraryWriter.toArray(new Song[0]);

		for (int i = 0; i < musicLibraryWriter.size(); i++) {

			out.println(s[i].getArtist());
			out.println(s[i].getTitle());
			out.println(s[i].getAlbum());
			out.print(s[i].getGenre());

			if (s[i].getPlaylists() != null) {

				ArrayList<String> playlists = s[i].getPlaylists();

				for (int j = 0; j < playlists.size(); j++) {
					out.print(";");
					out.print(playlists.get(j));
				}
				out.println();
			} else {
				out.println();
			}

			if (i < musicLibraryWriter.size() - 1) {
				out.println();
			}
		}

		out.close();
	}
}
