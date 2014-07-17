package io.enisher.benchmarks.infra;

import org.openjdk.jmh.infra.Blackhole;

/**
 * @author <a href="mailto:enisher@gmail.com">Artem Orobets</a>
 */
public interface Super {

  int WORK_AMOUNT = 40;

  int calc();

  public static class Empty1 implements Super {
    @Override
    public int calc() {
      return 1;
    }
  }

  public static class Empty2 implements Super {
    @Override
    public int calc() {
      return 2;
    }
  }

  public static class Empty3 implements Super {
    @Override
    public int calc() {
      return 3;
    }
  }

  public static class Empty4 implements Super {
    @Override
    public int calc() {
      return 4;
    }
  }

  public static class Empty5 implements Super {
    @Override
    public int calc() {
      return 5;
    }
  }

  public static class Empty6 implements Super {
    @Override
    public int calc() {
      return 6;
    }
  }

  public static class Empty7 implements Super {
    @Override
    public int calc() {
      return 7;
    }
  }

  public static class Empty8 implements Super {
    @Override
    public int calc() {
      return 8;
    }
  }

  public static class Worker1 implements Super {
    @Override
    public int calc() {
      Blackhole.consumeCPU(WORK_AMOUNT);
      return 1;
    }
  }

  public static class Worker2 implements Super {
    @Override
    public int calc() {
      Blackhole.consumeCPU(WORK_AMOUNT);
      return 2;
    }
  }

  public static class Worker3 implements Super {
    @Override
    public int calc() {
      Blackhole.consumeCPU(WORK_AMOUNT);
      return 3;
    }
  }

  public static class Worker4 implements Super {
    @Override
    public int calc() {
      Blackhole.consumeCPU(WORK_AMOUNT);
      return 4;
    }
  }

  public static class Worker5 implements Super {
    @Override
    public int calc() {
      Blackhole.consumeCPU(WORK_AMOUNT);
      return 5;
    }
  }

  public static class Worker6 implements Super {
    @Override
    public int calc() {
      Blackhole.consumeCPU(WORK_AMOUNT);
      return 6;
    }
  }

  public static class Worker7 implements Super {
    @Override
    public int calc() {
      Blackhole.consumeCPU(WORK_AMOUNT);
      return 7;
    }
  }

  public static class Worker8 implements Super {
    @Override
    public int calc() {
      Blackhole.consumeCPU(WORK_AMOUNT);
      return 8;
    }
  }
}
