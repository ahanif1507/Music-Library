package musicLibrary;

public class MusicLibraryTester {

	public static void main(String[] args) throws Exception {
		// Your testcases go here.
		System.out.println("Starting tests of MusicLibrary");

		// Testing addSong
		System.out.println("Testing addSong...");
		MusicLibrary testingAdd = new MusicLibrary();
		testingAdd.addSong("Atif Aslam", "Tajdar E Haram", "Coke Studio Season 8", "Pop", null);
		if (testingAdd.getAllSongs().length == 1) {
			System.out.println("\tPassed add song with no Playlist");
		}
		String[] testingAddPlaylist = new String[1];
		testingAddPlaylist[0] = "Desi Pop";
		testingAdd.addSong("Strings", "Duur", "Duur", "Pop", testingAddPlaylist);
		if (testingAdd.getAllSongs().length == 2) {
			System.out.println("\tPassed add song with Playlist");
		}

		MusicLibrary myMusic = new MusicLibrary();
		myMusic.loadMusicDb("tunes.txt");

		System.out.println("Testing getAllSongs()...");
		Song[] songs = myMusic.getAllSongs();
		if (songs.length == 68) {
			System.out.println("\tPassed add getAllSongs()");
		} else {
			System.out.println("\tFailed add getAllSongs()");
			return;
		}

		Song[] songs1 = testingAdd.getAllSongs();
		if (songs1.length == 2) {
			System.out.println("\tPassed add getAllSongs()");
		} else {
			System.out.println("\tFailed add getAllSongs()");
			return;
		}

		System.out.println("Testing loadMusicDB...");
		MusicLibrary myMusic01 = new MusicLibrary();
		myMusic01.loadMusicDb("tunes.txt");
		if (myMusic01.getAllSongs().length == 68) {
			System.out.println("\tPassed add loadMusicDB");
		} else {
			System.out.println("\tFailed add loadMusicDB");
			return;
		}

		MusicLibrary tunesMedium = new MusicLibrary();
		tunesMedium.loadMusicDb("tunes-medium.txt");
		if (tunesMedium.getAllSongs().length == 110) {
			System.out.println("\tPassed add loadMusicDB");
		} else {
			System.out.println("\tFailed add loadMusicDB");
			return;
		}

		System.out.println("Testing getAllArtists()...");
		String[] arr2 = myMusic.getAllArtists();
		if (arr2.length == 56) {
			System.out.println("\tPassed getAllArtists");
		} else {
			System.out.println("\tFailed getAllArtists");
			return;
		}

		String[] arr02 = testingAdd.getAllArtists();
		if (arr02.length == 2) {
			System.out.println("\tPassed getAllArtists");
		} else {
			System.out.println("\tFailed getAllArtists");
			return;
		}

		System.out.println("Testing getSongsByArtists()...");
		Song[] arr3 = (myMusic.getSongsByArtist("U2"));
		if (arr3.length == 3) {
			System.out.println("\tPassed getSongsByArtists()");
		} else {
			System.out.println("\tFailed getSongsByArtists()");
			return;
		}

		Song[] arr03 = (testingAdd.getSongsByArtist("Atif Aslam"));
		if (arr03.length == 1) {
			System.out.println("\tPassed getSongsByArtists()");
		} else {
			System.out.println("\tFailed getSongsByArtists()");
			return;
		}

		System.out.println("Testing getGenres()...");
		String[] arr4 = myMusic.getGenres();
		if (arr4.length == 8) {
			System.out.println("\tPassed getGenres()");
		} else {
			System.out.println("\tFailed getGenres()");
			return;
		}

		String[] arr04 = tunesMedium.getGenres();
		if (arr04.length == 37) {
			System.out.println("\tPassed getGenres()");
		} else {
			System.out.println("\tFailed getGenres()");
			return;
		}

		System.out.println("Testing getSongsByGenre()...");
		Song[] arr5 = (myMusic.getSongsByGenre("Alternative"));
		if (arr5.length == 15) {
			System.out.println("\tPassed getSongsByGenre()");
		} else {
			System.out.println("\tFailed getSongsByGenre()");
			return;
		}

		Song[] arr05 = (tunesMedium.getSongsByGenre("desi pop"));
		if (arr05.length == 20) {
			System.out.println("\tPassed getSongsByGenre()");
		} else {
			System.out.println("\tFailed getSongsByGenre()");
			return;
		}

		System.out.println("Testing getPlaylists()...");
		String[] arr6 = myMusic.getPlaylists();
		if (arr6.length == 2) {
			System.out.println("\tPassed getPlaylists()");
		} else {
			System.out.println("\tFailed getPlaylists()");
			return;
		}

		String[] arr06 = tunesMedium.getPlaylists();
		if (arr06.length == 4) {
			System.out.println("\tPassed getPlaylists()");
		} else {
			System.out.println("\tFailed getPlaylists()");
			return;
		}

		System.out.println("Testing getSongsByPlaylist...");
		Song[] arr7 = (myMusic.getSongsByPlaylist("Road tunes"));
		if (arr7.length == 38) {
			System.out.println("\tPassed getSongsByPlaylist");
		} else {
			System.out.println("\tFailed getSongsByPlaylist");
			return;
		}

		Song[] arr07 = (tunesMedium.getSongsByPlaylist("Bollywood Blast"));
		if (arr07.length == 40) {
			System.out.println("\tPassed getSongsByPlaylist");
		} else {
			System.out.println("\tFailed getSongsByPlaylist");
			return;
		}

		System.out.println("Testing createPlaylist...");
		myMusic.createPlaylist("Faves");
		if (myMusic.getPlaylists().length == 3) {
			System.out.println("\tPassed createPlaylist");
		} else {
			System.out.println("\tFailed createPlaylist");
			return;
		}

		tunesMedium.createPlaylist("Faves");
		if (tunesMedium.getPlaylists().length == 5) {
			System.out.println("\tPassed createPlaylist");
		} else {
			System.out.println("\tFailed createPlaylist");
			return;
		}

		System.out.println("Testing addSongToPlaylist...");
		myMusic.addSongToPlaylist("Time Stand Still", "Faves");
		if (myMusic.getSongsByPlaylist("Faves").length == 1) {
			System.out.println("\tPassed addSongToPlaylist");
		} else {
			System.out.println("\tFailed addSongToPlaylist");
			return;
		}

		tunesMedium.addSongToPlaylist("abcdef", "Faves");
		if (tunesMedium.getSongsByPlaylist("Faves").length == 0) {
			System.out.println("\tPassed addSongToPlaylist");
		} else {
			System.out.println("\tFailed addSongToPlaylist");
			return;
		}

		System.out.println("Testing writeMusicDb...");
		myMusic.writeMusicDb("testcase.txt");
		System.out.print("\tPassed writeMusicDb... ");
		System.out.print("testcase.txt created\n");
		tunesMedium.writeMusicDb("testcase2.txt");
		System.out.print("\tPassed writeMusicDb... ");
		System.out.print("testcase2.txt created");

		return;
	}

}
