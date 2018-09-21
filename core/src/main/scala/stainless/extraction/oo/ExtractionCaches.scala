/* Copyright 2009-2018 EPFL, Lausanne */

package stainless
package extraction
package oo

trait ExtractionCaches extends extraction.ExtractionCaches { self: ExtractionPipeline =>

  private class ClassKey(private val cd: s.ClassDef) extends SimpleKey {
    override def dependencies = Set(cd.id)

    private val key = (
      cd.id,
      cd.typeArgs,
      cd.parents,
      cd.fields.map(_.toVariable),
      cd.flags
    )

    override def hashCode: Int = key.hashCode
    override def equals(that: Any): Boolean = that match {
      case ck: ClassKey => (cd eq ck.cd) || (key == ck.key)
      case _ => false
    }
  }

  protected implicit object ClassKey extends SimpleKeyable[s.ClassDef] {
    override def apply(cd: s.ClassDef, symbols: s.Symbols): SimpleKey = new ClassKey(cd)
  }

  override protected def getSimpleKey(id: Identifier)(implicit symbols: s.Symbols): SimpleKey =
    symbols.lookupClass(id).map(new ClassKey(_)).getOrElse(super.getSimpleKey(id))


  private class ClassDependencyKey private(cd: s.ClassDef)(implicit symbols: s.Symbols)
    extends DefinitionDependencyKey(cd)(symbols)

  protected implicit object ClassDependencyKey extends DependencyKeyable[s.ClassDef] {
    override def apply(cd: s.ClassDef, symbols: s.Symbols): DependencyKey = new ClassDependencyKey(cd)(symbols)
  }

  override protected def getDependencyKey(id: Identifier)(implicit symbols: s.Symbols): DependencyKey =
    symbols.lookupClass(id).map(ClassDependencyKey(_, symbols)).getOrElse(super.getDependencyKey(id))
}
