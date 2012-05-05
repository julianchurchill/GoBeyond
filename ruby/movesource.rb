require 'test/unit'
require 'java'
$CLASSPATH << "C:\\Users\\Jennie\\git\\GoBeyond\\bin"

Move = com.chewielouie.gobeyond.Move
Coord = com.chewielouie.gobeyond.util.Coord
SimpleBoard = com.chewielouie.gobeyond.SimpleBoard
Board = com.chewielouie.gobeyond.Board

class TestMoveSource < Test::Unit::TestCase
  def setup
    @source = MoveSource.new
    @board = SimpleBoard::makeBoard('ww.'+
                                    'bww'+
                                    'bbw');
  end

  def testSimpleBoardRespondsToEach
    assert SimpleBoard.new( 9 ).respond_to?("each")
  end

  def testSourceReturnsMoveWithMatchingColour
    assert_equal( Move::Colour::White, @source.getMove( Move::Colour::White, @board ).colour )
    assert_equal( Move::Colour::Black, @source.getMove( Move::Colour::Black, @board ).colour )
  end

  def testSourceReturnsOnlyEmptyPoints
    assert_equal( Move.new( Coord.new( 2, 0 ), Move::Colour::White ), @source.getMove( Move::Colour::White, @board ) )
    @board = SimpleBoard::makeBoard('www'+
                                   'b.w'+
                                   'bbw');
    assert_equal( Move.new( Coord.new( 1, 1 ), Move::Colour::White ), @source.getMove( Move::Colour::White, @board ) )
  end
end

class Move
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
      return Move.new( board.first_empty_point, colour )
    end
  end
end
