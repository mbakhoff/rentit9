package esi.rentit9.interop;

public enum InteropImplementation {

    Team9 {
        @Override
        public BuilditInterop getImpl() {
            return new Team9Interop();
        }
    },

    Team1 {
        @Override
        public BuilditInterop getImpl() {
            return new Team1Interop();
        }
    },

    Dummy {
        @Override
        public BuilditInterop getImpl() {
            return new DummyInterop();
        }
    };

    public abstract BuilditInterop getImpl();

}
