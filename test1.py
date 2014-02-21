# this program is written for calculate max combo with puzzle and dragons
# puzzle and dragons has a 5*6 game board like this:
# F F F F F F
# A A A A A A
# ...
# H H H H H H
#
#
##

from random import *

ELEMENTS = ['AQUA','FIRE','WOOD','LIGHT','DARK','HEAL','BLOCK']
ELEMENT_VALUE = ['1','2','3','4','5','6','-1']

ELEMENT_DICT = dict(zip(ELEMENT_VALUE,ELEMENTS))

BoardHeight = 5
BoardWidth = 6

GameBoard = []  # Gameboard is a board like ['W','A',...][..]..

def printGameBoard(board):
    for row in range(BoardHeight):
        item_be_printed = ''
        for col in range(BoardWidth):
            item_be_printed += board[row*BoardWidth+col]
            item_be_printed += ';'
        item_be_printed = item_be_printed[0:len(item_be_printed)-1]
        print (item_be_printed)


def InitialRandomBoard():
    for i in range(BoardHeight * BoardWidth):
        GameBoard[i].append(ELEMENT_DICT[str(randint(1,6))][0])
    printGameBoard(GameBoard)

def InitialDefiniteBoard(board_string):  # read a string like 'A F C' to generate board
    item_count = 0
    for i in range(BoardHeight * BoardWidth):
        if (len(board_string.split()) > i):
            GameBoard.append(board_string.split()[i])
            item_count += 1
    while (item_count < BoardWidth * BoardHeight):
        GameBoard.append(ELEMENT_DICT[str(randint(1,6))][0])
        item_count += 1

    printGameBoard(GameBoard)

# main program body

#InitialRandomBoard()
InitialDefiniteBoard('A A A A A A')