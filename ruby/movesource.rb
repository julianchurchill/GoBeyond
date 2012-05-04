require 'test/unit'
require 'java'
$CLASSPATH << "C:\\Users\\Jennie\\git\\GoBeyond\\bin"

Move = com.chewielouie.gobeyond.Move
Coord = com.chewielouie.gobeyond.util.Coord

class TestMoveSource < Test::Unit::TestCase
    def testSourceReturnsAPass
        source = MoveSource.new
        assert_equal( source.getMove( Move::Colour::Black, nil ), Move.new( Coord.new( -1, -1 ), Move::Colour::Black ) )
    end
end

class MoveSource 
	include Java::com.chewielouie.gobeyond.MoveSource
 def getMove(colour, board)
    return Move.new( Coord.new( -1, -1 ), colour )
 end
end
