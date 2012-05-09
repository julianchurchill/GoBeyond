require 'java'
$CLASSPATH << "C:\\Users\\Jennie\\git\\GoBeyond\\bin"

Move = com.chewielouie.gobeyond.Move
Coord = com.chewielouie.gobeyond.util.Coord
SimpleBoard = com.chewielouie.gobeyond.SimpleBoard
Board = com.chewielouie.gobeyond.Board

class Move
    def inspect
        return toString()
    end
end

class Coord
  def inspect
    return toString()
  end
end

class BoardPoint
  attr_reader :colour
  attr_reader :coord
  def initialize( colour, coord )
    @colour = colour
    @coord = coord
  end
end

class SimpleBoard
  def each
    for y in 0...size
      for x in 0...size
        c = Coord.new( x, y )
        yield BoardPoint.new( getContentsOfPoint(c), c )
      end
    end
  end

  def first_empty_point
    self.each { |point| return point.coord if point.colour == Board::Point::Empty }
  end
end

class MoveSource
    include Java::com.chewielouie.gobeyond.MoveSource
  def getMove(colour, board)
    if board.respond_to?("first_empty_point")
      move = Move.new( board.first_empty_point, colour )
      return move if not isEye( move, board )
    end
    return Move::passMove( colour )
  end

  def isEye( move, board )
    friendlyBoardPoint = Board::Point::BlackStone;
    if move.colour == Move::Colour::White
      friendlyBoardPoint = Board::Point::WhiteStone;
    end
    return false if not allLibertiesOfPointAreFriendly( move.coord(), friendlyBoardPoint, board )
    return countFriendlyPoints( move.coord(), friendlyBoardPoint, board ) >= 7
  end

  def allLibertiesOfPointAreFriendly( coord, friendlyBoardPoint, board )
    return false if not isFriendlyPoint( coord.up(), friendlyBoardPoint, board )
    return false if not isFriendlyPoint( coord.down(), friendlyBoardPoint, board )
    return false if not isFriendlyPoint( coord.left(), friendlyBoardPoint, board )
    return false if not isFriendlyPoint( coord.right(), friendlyBoardPoint, board )
    return true
  end

  def isFriendlyPoint( coord, colour, board )
    return (board.getContentsOfPoint( coord ) == Board::Point::OffBoard or board.getContentsOfPoint( coord ) == colour)
  end

  def countFriendlyPoints( coord, colour, board )
    count = 0
    count = count + 1 if isFriendlyPoint( coord.up(), colour, board )
    count = count + 1 if isFriendlyPoint( coord.upLeft(), colour, board )
    count = count + 1 if isFriendlyPoint( coord.upRight(), colour, board )
    count = count + 1 if isFriendlyPoint( coord.down(), colour, board )
    count = count + 1 if isFriendlyPoint( coord.downLeft(), colour, board )
    count = count + 1 if isFriendlyPoint( coord.downRight(), colour, board )
    count = count + 1 if isFriendlyPoint( coord.left(), colour, board )
    count = count + 1 if isFriendlyPoint( coord.right(), colour, board )
    return count
  end
end
