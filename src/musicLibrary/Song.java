package musicLibrary;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * The Song class. A song object stores information about an individual song.
 *
 */
public class Song {
	private String artist;
	private String title;
	private String album;
	private String genre;
	private ArrayList<String> playlists = new ArrayList<String>();

	/**
	 * The constructor for a song
	 * 
	 * @param artist The artist of the song
	 * @param title  The title of the song
	 * @param album  The album the song is in
	 * @param genre  The genre of the song
	 */
	public Song(String artist, String title, String album, String genre) {
		setArtist(artist);
		setTitle(title);
		setAlbum(album);
		setGenre(genre);
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		String[] s = genre.split(";");

		if (s.length < 2) {
			this.genre = s[0];
		} else {
			this.genre = s[0];
			for (int i = 1; i < s.length; i++) {
				playlists.add(s[i]);
			}
			Collections.sort(this.playlists);
		}
	}

	public ArrayList<String> getPlaylists() {
		return this.playlists;
	}
	
	public void addToPlaylist(String playlistName) {
		this.playlists.add(playlistName);
		Collections.sort(this.playlists);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((album == null) ? 0 : album.hashCode());
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Song other = (Song) obj;
		if (album == null) {
			if (other.album != null) {
				return false;
			}
		} else if (!album.equals(other.album)) {
			return false;
		}
		if (artist == null) {
			if (other.artist != null) {
				return false;
			}
		} else if (!artist.equals(other.artist)) {
			return false;
		}
		if (genre == null) {
			if (other.genre != null) {
				return false;
			}
		} else if (!genre.equals(other.genre)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		if (playlists == null) {
			return "Song [artist=" + artist + ", title=" + title + ", album=" + album 
					+ ", genre=" + genre + ", playlists=[]]";
		} else {
			return "Song [artist=" + artist + ", title=" + title + ", album=" + album 
					+ ", genre=" + genre + ", playlists=" + playlists + "]";
		}
	}

}
