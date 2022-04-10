package musicLibrary;

import java.util.Scanner;

/**
 * A text driven interface for the MusicLibrary.
 * 
 * You will need to fill in all of the missing functionality. Most of the
 * functionality is simply getting some input from the user and then calling the
 * appropriate methods in MusicLibrary.
 * 
 * Don't change the menu options, as that will make me very sad while testing.
 *
 */
public class MusicLibraryManager {

	public static void main(String[] args) {
		MusicLibrary myMusic = new MusicLibrary();
		Scanner inp = new Scanner(System.in);

		while (true) {
			System.out.println("=================================================");
			System.out.println("Welcome to the Music Library Manager!");
			System.out.println("Please choose from one of the following options:");
			System.out.println("\t1) Print all the songs in the library");
			System.out.println("\t2) Print all the songs in the library, sorted by title");
			System.out.println("\t3) Print all the songs in the library, sorted by artist");
			System.out.println("\t4) Print all the songs in the library, sorted by album");
			System.out.println("\t5) Add a song to the library");
			System.out.println("\t6) List all artists");
			System.out.println("\t7) List all songs by a specific artist");
			System.out.println("\t8) List all genres");
			System.out.println("\t9) List all songs in a specific genre");
			System.out.println("\t10) Create a playlist");
			System.out.println("\t11) Add a song to a playlist");
			System.out.println("\t12) List all songs in a playlist");
			System.out.println("\t13) Load songs from a file");
			System.out.println("\t14) Write all songs out to a file");
			System.out.println("\t15) Exit");
			System.out.println("");
			System.out.print("Your choice: ");

			String line = inp.nextLine();
			if (line.equals("1")) {
				Song[] songs = myMusic.getAllSongs();
				if (songs.length == 0) {
					System.out.println("The library is empty!");
				} else {
					for (Song s : songs) {
						System.out.println(s);
					}
				}
			} else if (line.equals("2")) {
				String[] titles = myMusic.getTitles();
				if (titles.length == 0) {
					System.out.println("The library is empty!");
				} else {
					for (int i = 0; i < titles.length; i++) {
						Song[] songs = myMusic.getSongsByTitle(titles[i]);
						for (Song s : songs) {
							System.out.println(s);
						}
					}
				}
			} else if (line.equals("3")) {
				String[] artists = myMusic.getAllArtists();
				if (artists.length == 0) {
					System.out.println("The library is empty!");
				} else {
					for (int i = 0; i < artists.length; i++) {
						Song[] songs = myMusic.getSongsByArtist(artists[i]);
						for (Song s : songs) {
							System.out.println(s);
						}
					}
				}
			} else if (line.equals("4")) {
				String[] albums = myMusic.getAlbums();
				if (albums.length == 0) {
					System.out.println("The library is empty!");
				} else {
					for (int i = 0; i < albums.length; i++) {
						Song[] songs = myMusic.getSongsByAlbum(albums[i]);
						for (Song s : songs) {
							System.out.println(s);
						}
					}
				}
			} else if (line.equals("5")) {
				System.out.print("Artist: ");
				String artist = inp.nextLine();
				System.out.print("Song title: ");
				String title = inp.nextLine();
				System.out.print("Album: ");
				String album = inp.nextLine();
				System.out.print("Genre: ");
				String genre = inp.nextLine();
				try {
					myMusic.addSong(artist, title, album, genre, null);
				} catch (SongAlreadyExistsException e) {
					System.out.println("Error: That song already exists");
				}
			} else if (line.equals("6")) {
				String[] artists = myMusic.getAllArtists();
				for (int i = 0; i < artists.length; i++) {
					System.out.println((artists[i]));
				}
			} else if (line.equals("7")) {
				Scanner artistName = new Scanner(System.in);
				System.out.print("Enter the artist name: ");
				String artist = artistName.nextLine();
				Song[] songs = (myMusic.getSongsByArtist(artist));
				for (int i = 0; i < songs.length; i++) {
					System.out.println((songs[i]));
				}
			} else if (line.equals("8")) {
				String[] genres = myMusic.getGenres();
				for (int i = 0; i < genres.length; i++) {
					System.out.println((genres[i]));
				}
			} else if (line.equals("9")) {
				Scanner genreName = new Scanner(System.in);
				System.out.print("Enter the genre: ");
				String genre = genreName.nextLine();
				Song[] songs = (myMusic.getSongsByGenre(genre));
				for (int i = 0; i < songs.length; i++) {
					System.out.println((songs[i]));
				}
			} else if (line.equals("10")) {
				Scanner playlistName = new Scanner(System.in);
				System.out.print("Enter the name of the playlist you want to create: ");
				String playlist = playlistName.nextLine();
				myMusic.createPlaylist(playlist);
			} else if (line.equals("11")) {
				Scanner playlistName = new Scanner(System.in);
				System.out.print("Enter the name of the playlist: ");
				String playlist = playlistName.nextLine();

				System.out.println();

				Scanner songName = new Scanner(System.in);
				System.out.print("Enter the title of the song you want to add: ");
				String song = songName.nextLine();
				System.out.println();

				try {
					myMusic.addSongToPlaylist(song, playlist);
				} catch (PlaylistNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (line.equals("12")) {

				Scanner playlistName = new Scanner(System.in);
				System.out.print("Enter the name of the playlist: ");
				String playlist = playlistName.nextLine();

				try {
					Song[] songs = (myMusic.getSongsByPlaylist(playlist));
					for (int i = 0; i < songs.length; i++) {
						System.out.println((songs[i]));
					}
				} catch (PlaylistNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (line.equals("13")) {
				Scanner filename = new Scanner(System.in);
				System.out.print("Enter the file name: ");
				String file = filename.nextLine();
				myMusic.loadMusicDb(file);

			} else if (line.equals("14")) {
				Scanner filename = new Scanner(System.in);
				System.out.print("Enter the file name to write to: ");
				String file = filename.nextLine();
				myMusic.writeMusicDb(file);

			} else if (line.equals("15")) {
				System.out.println("Thank you for using the music manager!");
				break;
			} else {
				System.out.println("Invalid input.");
				continue;
			}
		}
	}
}
