package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import tubeVideosManager.Genre;
import tubeVideosManager.TubeVideosManager;

/**
 * 
 * You need student tests if you are asking for help during office hours about
 * bugs in your code. Feel free to use tools available in TestingSupport.java
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {

	@Test
	public void addVideos() {
		TubeVideosManager test = new TubeVideosManager();
		test.addVideoToDB("Cars", "url", 130, Genre.Comedy);
		System.out.println(test.getAllVideosInDB());
	}

	@Test
	public void getVideos() {
		TubeVideosManager test = new TubeVideosManager();
		test.addVideoToDB("Cars", "url", 130, Genre.Comedy);
		test.addVideoToDB("Robot", "url", 1, Genre.FilmAnimation);
		test.addVideoToDB("Penguins", "url", 100000000, Genre.Music);
		System.out.println(test.getAllVideosInDB());

	}

	@Test
	public void findVideos() {
		TubeVideosManager test = new TubeVideosManager();
		test.addVideoToDB("Cars", "url", 130, Genre.Comedy);
		test.addVideoToDB("Robot", "url", 1, Genre.FilmAnimation);
		test.addVideoToDB("Penguins", "url", 100000000, Genre.Music);
		assertTrue(test.findVideo("Cars") != null);
	}

	@Test
	public void addComments() {
		TubeVideosManager test = new TubeVideosManager();
		test.addVideoToDB("Surfs Up", "yup", 100, Genre.Documentary);
		test.addVideoToDB("Cars", "url", 130, Genre.Comedy);
		test.addVideoToDB("Robot", "url", 1, Genre.FilmAnimation);
		test.addVideoToDB("Penguins", "url", 100000000, Genre.Music);
		test.addPlaylist("Movie");
		test.addVideoToPlaylist("Movie", "Cars");
		test.addComments("Surfs Up", "Good Movie");
		test.addComments("Surfs Up", "Great Movie");
		test.addComments("Cars", "Good Movie");
		test.addComments("Cars", "Great Movie");
		test.addComments("Robot", "Good Movie");
		test.addComments("Robot", "Great Movie");
		test.addComments("Penguins", "Good Movie");
		test.addComments("Penguins", "Great Movie");
		System.out.println(test.getAllVideosInDB().get(2).getComments().get(0));
		System.out.println(test.getAllVideosInDB().get(0).getComments().get(1));
		assertTrue(true);
	}

	@Test
	public void addPlaylist() {
		TubeVideosManager test = new TubeVideosManager();
		test.addPlaylist("Music");
		System.out.println(test.getPlaylistsNames()[0]);
	}

	@Test
	public void getPlaylistName() {
		TubeVideosManager test = new TubeVideosManager();
		test.addPlaylist("Music");
		test.addPlaylist("Bops");
		test.addPlaylist("Bangers");
		System.out.println(test.getPlaylistsNames()[1]);
	}

	@Test
	public void addVideoPlaylist() {
		TubeVideosManager test = new TubeVideosManager();
		test.addPlaylist("Music");
		test.addVideoToDB("Cars", "url", 130, Genre.Comedy);
		test.addVideoToPlaylist("Cars", "Music");
		System.out.println(test.getPlaylist("Music"));
	}

	@Test
	public void getPlaylist() {
		TubeVideosManager test = new TubeVideosManager();
		test.addPlaylist("Music");
		System.out.println(test.getPlaylist("Music").toString());
	}

	@Test
	public void clear() {
		TubeVideosManager test = new TubeVideosManager();
		test.addVideoToDB("Cars", "url", 130, Genre.Comedy);
		test.addVideoToDB("Robot", "url", 1, Genre.FilmAnimation);
		test.addVideoToDB("Penguins", "url", 100000000, Genre.Music);
		test.addPlaylist("Music");
		test.addPlaylist("Bops");
		test.addPlaylist("Bangers");
		System.out.println(test.getAllVideosInDB());
		System.out.println(Arrays.toString(test.getPlaylistsNames()));
		test.clearDatabase();
		System.out.println(test.getAllVideosInDB());
		System.out.println(Arrays.toString(test.getPlaylistsNames()));
	}

	@Test
	public void searchVideo() {
		TubeVideosManager test = new TubeVideosManager();
		test.addPlaylist("Music");
		test.addPlaylist("Bops");
		test.addPlaylist("Bangers");
		test.addVideoToDB("Cars", "url", 130, Genre.Comedy);
		test.addVideoToDB("Robot", "url", 1, Genre.FilmAnimation);
		test.addVideoToDB("Penguins", "url", 100000000, Genre.Music);
		test.addVideoToPlaylist("Cars", "Music");
		test.addVideoToPlaylist("Robot", "Bops");
		test.addVideoToPlaylist("Penguins", "Music");
		System.out.println(test.searchForVideos(null, null, -1, null));
	}

}
