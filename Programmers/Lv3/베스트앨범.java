package Lv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//해쉬맵 쓰라는데 조건 정렬하려고 클래스담는 2차원 리스트로 만들었다

public class 베스트앨범 {
//	static List<HashMap<String, Integer>> genres_kind = new ArrayList<HashMap<String, Integer>>();
	static List<List<Music>> genres_kind = new ArrayList<List<Music>>();
	static List<int[]> genres_sumPlays = new ArrayList<int[]>();

	static class arraysCom implements Comparator<int[]> {

		@Override
		public int compare(int[] o1, int[] o2) {
			return Integer.compare(o1[1], o2[1]);
		}
	}

	static class Music implements Comparable<Music> {
		String genre;
		int play;
		int index;

		public Music(String genre, int play, int index) {
			super();
			this.genre = genre;
			this.play = play;
			this.index = index;
		}

		@Override
		public int compareTo(Music o) {
			if (this.play == o.play)
				return this.index - o.index;
			else
				return o.play - this.play;
		}

		@Override
		public String toString() {
			return "Music [genre=" + genre + ", play=" + play + ", index=" + index + "]";
		}

	}

	public static void main(String[] args) {
		String[] input = { "classic", "pop", "classic", "classic", "가요"};
		int[] plays = { 500, 600, 600, 800,2000};

		int[] x = solution(input, plays);
		System.out.println(Arrays.toString(x));
	}

	static int[] solution(String[] input, int[] plays) {
		for (int i = 0, index = 0; i < input.length; i++) {
			if (i == 0) {
				genres_kind.add(new ArrayList<Music>());
				genres_kind.get(index++).add(new Music(input[i], plays[i], i));
				System.out.println(genres_kind.size());
				System.out.println(new Music(input[i], plays[i], i).toString());
				genres_sumPlays.add(new int[] { i, plays[i] });
			} else { // 1이상부터는 비교하면서 넣어줌
				boolean flag = false;
				for (int j = 0; j < genres_kind.size(); j++) {
					if (genres_kind.get(j).get(0).genre.equals(input[i])) { // String 비교 주의 프로그래머스에서는 == 안봐줌
						genres_kind.get(j).add(new Music(input[i], plays[i], i));
						int[] x = { i, genres_sumPlays.get(j)[1] + plays[i] };
						genres_sumPlays.set(j, x);
						flag = true;
						break;
					}
				} // 새로운 장르는 행추가
				if (!flag) {
					genres_kind.add(new ArrayList<Music>());
					System.out.println(genres_kind.size());
					genres_kind.get(index++).add(new Music(input[i], plays[i], i));
					genres_sumPlays.add(new int[] { i, plays[i] });
				}
			}
		} // end input

		// 이제 가장 많이 플레이 된 두 장르순으로 두 개 씩 저장
		int[] result = new int[genres_kind.size() * 2];
		int index = 0;
		Collections.sort(genres_sumPlays, new arraysCom());

		for (int i = 0; i < genres_kind.size(); i++)
			Collections.sort(genres_kind.get(i));

		for (int i = genres_sumPlays.size() - 1; i >= 0; i--) {
			int[] x = genres_sumPlays.get(i);

			for (int j = 0; j < genres_kind.size(); j++) {
				for (int k = 0; k < genres_kind.get(j).size(); k++) {
					if (x[0] == genres_kind.get(j).get(k).index) { //가장 누적 순에 저장된 인덱스를 가지고 있는 장르 찾아서
						if (genres_kind.get(j).size() > 1) { //2개이상일 때는 두개선택
							result[index++] = genres_kind.get(j).get(0).index;
							result[index++] = genres_kind.get(j).get(1).index;
						} else { //적으면 한개만
							result[index++] = genres_kind.get(j).get(0).index;
						}
					}
				}

			}
		} //인덱스 까지만만리턴
		int[] y = new int[index];
		for(int i = 0; i< index; i++) {
			y[i] = result[i];
		}
		return y;
	}
}
