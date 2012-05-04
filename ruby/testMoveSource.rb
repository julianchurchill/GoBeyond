require 'java'

Move = com.chewielouie.gobeyond.Move
Coord = com.chewielouie.gobeyond.util.Coord

class MoveSource 
	include Java::com.chewielouie.gobeyond.MoveSource
 def getMove(colour, board)
    return Move.new( Coord.new( 1, 2 ), Move::Colour::Black )
 end
end

#def zero
#  return 0
#end