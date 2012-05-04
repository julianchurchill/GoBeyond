require 'java'

Move = com.chewielouie.gobeyond.Move
Coord = com.chewielouie.gobeyond.util.Coord

class MoveSource 
	include Java::com.chewielouie.gobeyond.MoveSource
 def getMove(colour, board)
    return Move.new( Coord.new( -1, -1 ), colour )
 end
end
