import os
from collections import defaultdict
from collections import deque
import random
from time import sleep

reset_colour = '\033[0;0m'

class Cell:
    def to_print(self):
        pass

    def is_wall(self):
        return False


class Wall(Cell):
    def to_print(self):
        return 'X'

    def is_wall(self):
        return True


class EmptyCell(Cell):
    def to_print(self):
        return ' '

class ConsideredWall(Wall):
    def to_print(self):
        return '?'


class Maze:
    def __init__(self):
        with os.popen('stty size', 'r') as stty:
            rows, columns = stty.read().split()
            self.rows, self.columns = int(rows) - 1, int(columns)
        self.maze = defaultdict(lambda: defaultdict(Wall))
        self.dirty_rows = set(range(self.rows))
        self.rendered_rows = [None] * self.rows

    def render(self):
        if not self.dirty_rows:
            return
        for i in self.dirty_rows:
            row = ''.join(self.maze[i][j].to_print() for j in range(self.columns)) + reset_colour
            self.rendered_rows[i] = row
        self.dirty_rows = set()
        print('\n'.join(self.rendered_rows), flush=True)
        sleep(0.1)

    def neighbours(self, cell):
        row, col = cell
        candidates = [
            (row - 1, col),
            (row + 1, col),
            (row, col - 1),
            (row, col + 1)
        ]   

        def is_not_border(cell):
            row, col = cell
            return 0 < row < self.rows - 1 and 0 < col < self.columns - 1

        return list(filter(is_not_border, candidates))

    def set_empty(self, cell):
        row, col = cell
        self.maze[row][col] = EmptyCell()
        self.dirty_rows.add(row)

    def set_considered(self, cell):
        row, col = cell
        self.maze[row][col] = ConsideredWall()
        self.dirty_rows.add(row)

    def set_wall(self, cell):
        row, col = cell
        self.maze[row][col] = Wall()
        self.dirty_rows.add(row)

    def is_wall(self, cell):
        row, col = cell
        return self.maze[row][col].is_wall()

    def get_behind_wall(self, cell, wall):
        row, col = 2 * wall[0] - cell[0], 2 * wall[1] - cell[1]
        if 0 < row < self.rows - 1 and 0 < col < self.columns - 1:
            return row, col
        else:
            return None

       
maze = Maze()
maze.render()

starting_cell = (10, 10)

walls = deque((starting_cell, wall) for wall in maze.neighbours(starting_cell))
for _, wall in walls:
    maze.set_considered(wall)

maze.set_empty(starting_cell)
maze.render()
i = 0

while walls:
    random_index = random.randint(0, len(walls) - 1)
    cell, wall = walls[random_index]
    
    if behind_wall := maze.get_behind_wall(cell, wall):
        if maze.is_wall(behind_wall):
            maze.set_empty(behind_wall)
            maze.set_empty(wall)
            for neighbor in maze.neighbours(behind_wall):
                if maze.is_wall(neighbor):
                    walls.append((behind_wall, neighbor))
                    maze.set_considered(neighbor)
        else:
            maze.set_wall(wall)
    else:
        maze.set_wall(wall)

    del walls[random_index]

    maze.render()

maze.render()
