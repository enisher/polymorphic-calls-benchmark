/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package io.enisher.benchmarks;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import io.enisher.benchmarks.infra.E;
import io.enisher.benchmarks.infra.Super;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class PolymorphismOverhead {

  List<E>     list = new LinkedList<E>();
  List<Super> obj  = new LinkedList<Super>();

  @Benchmark
  @OperationsPerInvocation(8)
  public int baseline() {
    int s = 0;
    for (E e : list) {
      s += noop(e);
    }
    return s;
  }

  @Benchmark
  @OperationsPerInvocation(8)
  public int measure_if_else_sequence() {
    int s = 0;
    for (E e : list) {
      if (e.equals(E.E1)) {
        s += a1();
      } else if (e.equals(E.E2)) {
        s += a2();
      } else if (e.equals(E.E3)) {
        s += a3();
      } else if (e.equals(E.E4)) {
        s += a4();
      } else if (e.equals(E.E5)) {
        s += a5();
      } else if (e.equals(E.E6)) {
        s += a6();
      } else if (e.equals(E.E7)) {
        s += a7();
      } else if (e.equals(E.E8)) {
        s += a8();
      } else {
        s += a9();
      }
    }
    return s;
  }

  @Benchmark
  @OperationsPerInvocation(8)
  public int measure_switch() {
    int s = 0;
    for (E e : list) {
      switch (e) {
      case E1:
        s += a1();
        break;
      case E2:
        s += a2();
        break;
      case E3:
        s += a3();
        break;
      case E4:
        s += a4();
        break;
      case E5:
        s += a5();
        break;
      case E6:
        s += a6();
        break;
      case E7:
        s += a7();
        break;
      case E8:
        s += a8();
        break;
      default:
        s += a9();
      }
    }
    return s;
  }

  @Benchmark
  @OperationsPerInvocation(8)
  public int measure_polymorphic_call() {
    int s = 0;
    for (Super a : obj) {
      s += a.calc();
    }
    return s;
  }

  @Benchmark
  @OperationsPerInvocation(8)
  public int measure_if_else_polymorphism() {
    int s = 0;
    for (Super a : obj) {
      if (a instanceof Super.Empty1) {
        s += a1();
      } else if (a instanceof Super.Empty2) {
        s += a2();
      } else if (a instanceof Super.Empty3) {
        s += a3();
      } else if (a instanceof Super.Empty4) {
        s += a4();
      } else if (a instanceof Super.Empty5) {
        s += a5();
      } else if (a instanceof Super.Empty6) {
        s += a6();
      } else if (a instanceof Super.Empty7) {
        s += a7();
      } else if (a instanceof Super.Empty8) {
        s += a8();
      } else {
        s += a9();
      }
    }
    return s;
  }

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public int noop(E e) {
    return 7;
  }

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public int a1() {
    return 1;
  }

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public int a2() {
    return 2;
  }

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public int a3() {
    return 3;
  }

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public int a4() {
    return 4;
  }

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public int a5() {
    return 5;
  }

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public int a6() {
    return 6;
  }

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public int a7() {
    return 7;
  }

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public int a8() {
    return 8;
  }

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public int a9() {
    return 9;
  }

  @Setup()
  public void setup() {
    list.clear();
    list.add(E.E1);
    list.add(E.E2);
    list.add(E.E3);
    list.add(E.E4);
    list.add(E.E5);
    list.add(E.E6);
    list.add(E.E7);
    list.add(E.E8);
    Collections.shuffle(list);

    obj.clear();
    obj.add(new Super.Empty1());
    obj.add(new Super.Empty2());
    obj.add(new Super.Empty3());
    obj.add(new Super.Empty4());
    obj.add(new Super.Empty5());
    obj.add(new Super.Empty6());
    obj.add(new Super.Empty7());
    obj.add(new Super.Empty8());
    Collections.shuffle(obj);
  }

}
