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

def CheckNeighbor(element):
    # an element like e[n] which is ordinarily connected with e[n-1],e[n+1],e[n-6],e[n+6] and if any n+/-x out of range simply discard
    neighbor = []
    if element-1 >= 0:  neighbor.append(element-1)
    if element+1 <= 29: neighbor.append(element+1)
    if element-6 >= 0: neighbor.append(element-6)
    if element+6 <= 29: neighbor.append(element+6)
    return neighbor

def CheckBoard(board):   # check whether there's 3-match in a board, which args board is a 5*6 char array
    # checking algorithm: from b[0],
    pass

# test cases

def test():

    #InitialRandomBoard()
    InitialDefiniteBoard('A A A A A A')
    #print (CheckNeighbor(28))

# main program body
test()