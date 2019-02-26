import java.util.Arrays;
import java.util.PriorityQueue;

public class _5MazeII {
	public int shortestDistance(int[][] maze, int[] start, int[] dest) {
		int[][] distance = new int[maze.length][maze[0].length];
		for (int[] row : distance) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		distance[start[0]][start[1]] = 0;
		dijkstra(maze, start, distance);
		
		return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
	}

	private void dijkstra(int[][] maze, int[] start, int[][] distance) {
		int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
		PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> a[2] - b[2]); // 构造小根堆
		queue.offer(new int[] {start[0], start[1], 0});
		while (!queue.isEmpty()) {
			int[] s= queue.poll(); // 最小距离的点
			if (distance[s[0]][s[1]] < s[2]) {
				continue;
			}
			for (int[] dir : dirs) {
				int x = s[0] + dir[0], y = s[1] + dir[1], count = 0;
				while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
					count++;
					x += dir[0];
					y += dir[1];
				}
				if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
					distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
					queue.offer(new int[]{x - dir[0], y - dir[1], distance[x - dir[0]][y - dir[1]]});
				}
			}
		}
	}
}
