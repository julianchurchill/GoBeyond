# run with 'jruby -S rspec movesource_spec.rb'
require './movesource.rb'

describe "Simple board ruby extension" do
  it "responds to each" do
    SimpleBoard.new( 9 ).respond_to?("each").should == true
  end
end

describe "Move source" do
  before(:each) do
      @source = MoveSource.new
      @board = SimpleBoard::makeBoard('ww.'+
                                      'ww.'+
                                      '.ww');
  end

  context "asked for a move" do
    it "returns a move with the colour requested" do
      @source.getMove( Move::Colour::White, @board).colour.should == Move::Colour::White
      @source.getMove( Move::Colour::Black, @board).colour.should == Move::Colour::Black
    end

    it "returns only empty points" do
      @source.getMove( Move::Colour::White, @board).coord.should == Coord.new( 2, 0 )
      @board = SimpleBoard::makeBoard('www'+
                                      'w.w'+
                                      '..w');
      @source.getMove( Move::Colour::White, @board).coord.should == Coord.new( 1, 1 )
    end

    it "does not fill in full eyes passing instead" do
      @board = SimpleBoard::makeBoard('.www'+
                                      'w.ww'+
                                      'www.'+
                                      'wwww');
      @source.getMove( Move::Colour::White, @board ).should == Move::passMove( Move::Colour::White )
    end
  end
end

