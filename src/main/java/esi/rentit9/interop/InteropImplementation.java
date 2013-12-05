package esi.rentit9.interop;

public enum InteropImplementation implements BuilditInterop {

    Team9 {
        @Override
        public Rest getRest() {
            return new Team9Rest();
        }

        @Override
        public Soap getSoap() {
            throw new UnsupportedOperationException();
        }
    },

    Team1 {
        @Override
        public Rest getRest() {
            return new Team1Rest();
        }

        @Override
        public Soap getSoap() {
            throw new UnsupportedOperationException();
        }
    },

    Dummy {
        @Override
        public Rest getRest() {
            return new DummyRest();
        }

        @Override
        public Soap getSoap() {
            return new DummySoap();
        }
    }

}
